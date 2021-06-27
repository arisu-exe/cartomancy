package io.github.fallOut015.cartomancy.entity.projectile;

import io.github.fallOut015.cartomancy.particles.ParticleTypesCartomancy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class DivineArrowEntity extends AbstractArrowEntity {
    @Nullable
    private LivingEntity target; // TODO serialize? probably add as a data parameter so i can spawn particles on the client when something is being tracked
    @Nullable
    private UUID casterID;
    private int warmupDelayTicks;
    private boolean moving;

    public DivineArrowEntity(EntityType<? extends DivineArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.noPhysics = false;
        this.setBaseDamage(5);
        this.moving = false;
    }

    public void setCasterID(UUID casterID) {
        this.casterID = casterID;
    }
    public @Nullable ServerPlayerEntity getCaster() {
        if(this.casterID == null) {
            return null;
        }
        PlayerEntity player = this.level.getPlayerByUUID(this.casterID);
        if(player == null) {
            return null;
        } else {
            return (ServerPlayerEntity) player;
        }
    }

    @Override
    public void tick() {
        if(-- this.warmupDelayTicks <= 0) {
            if(this.level.isClientSide && this.tickCount % 4 == 0) {
                this.level.addParticle(ParticleTypesCartomancy.SHINE.get(), this.getX(), this.getY(), this.getZ(), (this.random.nextDouble() - 0.5d) * 0.001d, (this.random.nextDouble() - 0.5d) * 0.001d, (this.random.nextDouble() - 0.5d) * 0.001d);
                //this.level.addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), (this.random.nextDouble() - 0.5d) * 0.1d, (this.random.nextDouble() - 0.5d) * 0.1d, (this.random.nextDouble() - 0.5d) * 0.1d);
            } else {
                if(this.target != null && this.target == this.getCaster()) {
                    this.target.removeTag("targeted");
                    //this.target.setGlowing(false);
                    //this.setGlowing(false);
                    this.target = null;
                }
                if(this.target == null) {
                    List<Entity> entities = this.level.getEntities(this, this.getBoundingBox().inflate(32), entity -> entity instanceof LivingEntity && entity != this.getCaster());
                    LivingEntity target = null;
                    entities.sort((l, r) -> (int) (l.distanceTo(this) - r.distanceTo(this)));
                    for(Entity entity : entities) {
                        if(entity.isAlive() && !entity.getTags().contains("targeted")) {
                            target = (LivingEntity) entity;
                            target.addTag("targeted");
                            //target.setGlowing(true);
                            //this.setGlowing(true);
                            break;
                        }
                    }
                    this.target = target;
                } else {
                    if(!this.target.isAlive()) {
                        this.target.removeTag("targeted");
                        //this.target.setGlowing(false);
                        //this.setGlowing(false);
                        this.target = null;
                    }
                }

                if(this.getDeltaMovement().y() < 0 && !this.isNoGravity()) {
                    this.setNoGravity(true);
                    if(this.target == null) {
                        this.remove();
                    }
                }

                if(this.getDeltaMovement().y() < 0.25d && this.target != null) {
                    this.moving = true;
                }

                if(this.moving && this.target != null) {
                    Vector3d normalizedDelta =  new Vector3d(this.target.getX() - this.getX(), this.target.getY() - this.getY() + target.getEyeHeight(), this.target.getZ() - this.getZ()).normalize().scale(0.75d);
                    Vector3d motion = new Vector3d(MathHelper.lerp(0.01d, this.getDeltaMovement().x(), normalizedDelta.x()), MathHelper.lerp(0.2d, this.getDeltaMovement().y(), normalizedDelta.y()), MathHelper.lerp(0.2d, this.getDeltaMovement().z(), normalizedDelta.z()));
                    this.setDeltaMovement(motion);
                }
            }

            super.tick();
        }

        /*if(this.world.isRemote) {
            this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
        }*/
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if(result.getType() == RayTraceResult.Type.BLOCK) {
            return;
        }
        super.onHit(result);

        if(this.target != null) {
            this.target.removeTag("targeted");
            //this.target.setGlowing(false);
            //this.setGlowing(false);
        }

        this.remove();
    }

    @Override
    public void onRemovedFromWorld() {
        if(this.target != null) {
            this.target.removeTag("targeted");
            //this.target.setGlowing(false);
            this.target = null;
        }
        super.onRemovedFromWorld();
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        if(compound.contains("casterID")) {
            this.casterID = compound.getUUID("casterID");
        }
        super.readAdditionalSaveData(compound);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        if(this.casterID != null) {
            compound.putUUID("casterID", this.casterID);
        }
        super.addAdditionalSaveData(compound);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setWarmupDelayTicks(int warmupDelayTicks) {
        this.warmupDelayTicks = warmupDelayTicks;
    }
    public boolean renderWithWarmup() {
        return this.warmupDelayTicks <= 0;
    }
}