package io.github.fallOut015.cartomancy.entity.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SigilEntity extends Entity {
    private static final DataParameter<String> RESOURCE = EntityDataManager.defineId(SigilEntity.class, DataSerializers.STRING);

    public SigilEntity(EntityType<? extends SigilEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public void setResource(String resource) {
        this.entityData.set(RESOURCE, resource);
    }
    public String getResource() {
        return this.entityData.get(RESOURCE);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(RESOURCE, "");
    }
    @Override
    protected void readAdditionalSaveData(CompoundNBT compound) {
        compound.putString("resource", this.getResource());
    }
    @Override
    protected void addAdditionalSaveData(CompoundNBT compound) {
        this.setResource(compound.getString("resource"));
    }
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
