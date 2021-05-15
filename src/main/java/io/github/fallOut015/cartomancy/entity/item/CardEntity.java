package io.github.fallOut015.cartomancy.entity.item;

import io.github.fallOut015.cartomancy.item.CardItem;
import io.github.fallOut015.cartomancy.item.ItemsCartomancy;
import io.github.fallOut015.cartomancy.item.card.Card;
import io.github.fallOut015.cartomancy.item.card.EntityCard;
import io.github.fallOut015.cartomancy.particles.ParticleTypesCartomancy;
import io.github.fallOut015.cartomancy.tags.BlockTagsCartomancy;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class CardEntity extends Entity {
    private static final DataParameter<Optional<Card>> CARD = EntityDataManager.defineId(CardEntity.class, Card.OPTIONAL_CARD);
    private static final DataParameter<Boolean> ACTIVE = EntityDataManager.defineId(CardEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTUNED = EntityDataManager.defineId(CardEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Optional<UUID>> CASTER_ID = EntityDataManager.defineId(CardEntity.class, DataSerializers.OPTIONAL_UUID);
    private boolean thrown;
    private int activeTime;
    boolean canPickup;
    private int pickupTime;
    public float spin;
    private int altarTime;

    public CardEntity(EntityType<? extends CardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.thrown = false;
        this.activeTime = 0;
        this.canPickup = false;
        this.pickupTime = 10;
        this.spin = 2.5f;
        this.altarTime = 0;
    }

    public void setCardType(Card cardType) {
        this.entityData.set(CARD, Optional.of(cardType));
    }
    public Optional<Card> getCardType() {
        return this.entityData.get(CARD);
    }
    public void setActive(boolean active) {
        this.entityData.set(ACTIVE, active);
    }
    public boolean isActive() {
        return this.entityData.get(ACTIVE).booleanValue();
    }
    public void setAttuned(boolean attuned) {
        this.entityData.set(ATTUNED, attuned);
    }
    public boolean isAttuned() {
        return this.entityData.get(ATTUNED).booleanValue();
    }
    public void setCasterID(UUID casterID) {
        this.entityData.set(CASTER_ID, Optional.of(casterID));
    }
    public Optional<UUID> getCasterID() {
        return this.entityData.get(CASTER_ID);
    }

    public void setThrown() {
        this.thrown = true;
    }
    public boolean wasThrown() {
        return this.thrown;
    }

    public void setCaster(@Nullable ServerPlayerEntity caster) {
        if(caster != null) {
            this.setCasterID(caster.getUUID());
        }
    }
    public @Nullable ServerPlayerEntity getServerCaster() {
        PlayerEntity player = this.level.getPlayerByUUID(this.getCasterID().get());
        if(this.getCasterID().isPresent() && player instanceof ServerPlayerEntity) {
            return (ServerPlayerEntity) player;
        } else {
            return null;
        }
    }

    public int getActiveTime() {
        return this.activeTime;
    }
    public Random getRand() {
        return this.random;
    }

    public static boolean isAltar(World world, BlockPos pos) {
        if(world.getBlockState(pos).getBlock().is(BlockTagsCartomancy.ALTAR_BASES)) {
            BlockState northTorch = world.getBlockState(pos.north());
            BlockState eastTorch = world.getBlockState(pos.east());
            BlockState southTorch = world.getBlockState(pos.south());
            BlockState westTorch = world.getBlockState(pos.west());
            return northTorch.getBlock().is(BlockTagsCartomancy.ALTAR_CANDLES) && eastTorch.getBlock().is(BlockTagsCartomancy.ALTAR_CANDLES) && southTorch.getBlock().is(BlockTagsCartomancy.ALTAR_CANDLES) && westTorch.getBlock().is(BlockTagsCartomancy.ALTAR_CANDLES);
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.pickupTime > 0) {
            -- this.pickupTime;
        }

        if(this.wasThrown()) {
            if(this.isOnGround()) {
                this.thrown = false;
                if(!this.isActive()) {
                    this.setActive(true);
                }
                this.canPickup = true;
                this.spin = 0;
            } else {
                this.yRot += this.spin;
            }
        }

        if(!this.isAttuned() && this.isActive()) {
            this.setActive(false);
        }

        if(this.isActive()) {
            ++ this.activeTime;

            if(this.getCardType().isPresent()) {
                ((EntityCard) this.getCardType().get()).onEntityActiveTick(this);
            }
        }

        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        Vector3d vector3d = this.getDeltaMovement();
        float f = this.getEyeHeight() - 0.11111111F;
        if (this.isInWater() && this.getFluidHeight(FluidTags.WATER) > (double)f) {
            this.spin *= 0.95f;
            this.applyFloatMotion();
            if(this.getDeltaMovement().y() > 0) {
                this.thrown = false;
                if(!this.isActive()) {
                    this.setActive(true);
                }
                this.canPickup = true;
            }
        } else if (this.isInLava() && this.getFluidHeight(FluidTags.LAVA) > (double)f) {
            this.setUnderLavaMovement();
        } else if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        if (this.level.isClientSide) {
            this.noPhysics = false;
        } else {
            this.noPhysics = !this.level.noCollision(this);
            if (this.noPhysics) {
                this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());
            }
        }

        if (!this.onGround || getHorizontalDistanceSqr(this.getDeltaMovement()) > (double)1.0E-5F || (this.tickCount + this.getId()) % 4 == 0) {
            this.move(MoverType.SELF, this.getDeltaMovement());
            float f1 = 0.98F;
            if (this.onGround) {
                f1 = this.level.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock().getFriction() * 0.98F;
            }

            this.setDeltaMovement(this.getDeltaMovement().multiply(f1, 0.98D, f1));
            if (this.onGround) {
                Vector3d vector3d1 = this.getDeltaMovement();
                if (vector3d1.y < 0.0D) {
                    this.setDeltaMovement(vector3d1.multiply(1.0D, -0.5D, 1.0D));
                }
            }
        }

        boolean flag = MathHelper.floor(this.xo) != MathHelper.floor(this.getX()) || MathHelper.floor(this.yo) != MathHelper.floor(this.getY()) || MathHelper.floor(this.zo) != MathHelper.floor(this.getZ());
        int i = flag ? 2 : 40;
        if (this.tickCount % i == 0) {
            if (this.level.getFluidState(this.blockPosition()).is(FluidTags.LAVA) && !this.fireImmune()) {
                this.playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
            }
        }

        this.hasImpulse |= this.updateInWaterStateAndDoFluidPushing();
        if (!this.level.isClientSide) {
            double d0 = this.getDeltaMovement().subtract(vector3d).lengthSqr();
            if (d0 > 0.01D) {
                this.hasImpulse = true;
            }
        }

        if(isAltar(this.level, this.getOnPos())) {
            ++ this.altarTime;
        } else {
            if(this.altarTime > 0) {
                -- this.altarTime;
            }
        }
        if(this.altarTime == 1000) { // make faster at night (altar emits particles and will also emit more at night)
            this.setAttuned(true);
            if(this.isActive()) {
                this.setActive(false);
            }
            this.canPickup = true;
        }

        if(this.level.isClientSide) {
            if(this.isActive() && this.tickCount % 4 == 0) {
                this.level.addParticle(ParticleTypesCartomancy.SPARKLE.get(), this.getX() + (this.random.nextDouble() - 0.5) / 2f, this.getY(), this.getZ() + (this.random.nextDouble() - 0.5) / 2f, 0, 0, 0);
            } else if(!this.isActive() && !this.isAttuned() && isAltar(this.level, this.getOnPos()) && this.tickCount % 8 == 0) {
                this.level.addParticle(ParticleTypesCartomancy.SPARKLE_ATTUNEMENT.get(), this.getX() + (this.random.nextDouble() - 0.5) / 2f, this.getY(), this.getZ() + (this.random.nextDouble() - 0.5) / 2f, 0, 0, 0);
            }
        }

        // TODO glow
        // TODO move towards player
    }

    public void playerTouch(PlayerEntity entityIn) {
        if (!this.level.isClientSide) {
            ItemStack itemstack = new ItemStack(ItemsCartomancy.CARD.get());
            if(this.getCardType().isPresent()) {
                CardItem.putCard(itemstack, this.getCardType().get());
            }
            CardItem.setAttuned(itemstack, this.isAttuned());
            Item item = itemstack.getItem();

            ItemStack copy = itemstack.copy();
            if (this.pickupTime == 0 && this.canPickup && !this.isActive() && entityIn.inventory.add(itemstack)) {
                copy.setCount(copy.getCount() - itemstack.getCount());
                entityIn.take(this, 1);
                if (itemstack.isEmpty()) {
                    this.remove();
                    itemstack.setCount(1);
                }

                entityIn.awardStat(Stats.ITEM_PICKED_UP.get(item), 1);
            }
        }
    }
    private void applyFloatMotion() {
        Vector3d vector3d = this.getDeltaMovement();
        this.setDeltaMovement(vector3d.x * (double)0.99F, vector3d.y + (double)(vector3d.y < (double)0.06F ? 5.0E-4F : 0.0F), vector3d.z * (double)0.99F);
    }
    private void setUnderLavaMovement() {
        Vector3d vector3d = this.getDeltaMovement();
        this.setDeltaMovement(vector3d.x * (double)0.95F, vector3d.y + (double)(vector3d.y < (double)0.06F ? 5.0E-4F : 0.0F), vector3d.z * (double)0.95F);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CARD, Optional.empty());
        this.entityData.define(ACTIVE, false);
        this.entityData.define(ATTUNED, false);
        this.entityData.define(CASTER_ID, Optional.empty());
    }
    @Override
    protected void readAdditionalSaveData(CompoundNBT compound) {
        if(compound.contains("card")) {
            this.setCardType(Card.getFromID(compound.getString("card")));
        }
        this.setAttuned(compound.getBoolean("attuned"));
        if(compound.contains("caster")) {
            this.entityData.set(CASTER_ID, Optional.of(compound.getUUID("caster")));
        }
        this.setActive(compound.getBoolean("active"));
        this.canPickup = compound.getBoolean("canPickup");
    }
    @Override
    protected void addAdditionalSaveData(CompoundNBT compound) {
        if(this.getCardType().isPresent()) {
            compound.putString("card", this.getCardType().get().getID());
        }
        compound.putBoolean("attuned", this.isAttuned());
        if(this.entityData.get(CASTER_ID).isPresent()) {
            compound.putUUID("caster", this.entityData.get(CASTER_ID).get());
        }
        compound.putBoolean("active", this.isActive());
        compound.putBoolean("canPickup", this.canPickup);
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}