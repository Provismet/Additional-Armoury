package com.provismet.AdditionalArmoury.utility;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.mob.HostileEntity;

public class Util {
    public static boolean isFriendly (LivingEntity entity1, LivingEntity entity2) {
        if (entity1 == entity2) return true;
        else return Util.friendlyInternal(entity1, entity2) && Util.friendlyInternal(entity2, entity1);
    }

    private static boolean friendlyInternal (LivingEntity entity1, LivingEntity entity2) {
        if (entity1 instanceof Tameable tame && tame.getOwner() == entity2) return true;
        if (entity1.getAttacker() == entity2) return false;
        if (entity1.getAttacking() == entity2) return false;
        if (entity1.getScoreboardTeam() == entity2.getScoreboardTeam()) return true;
        if (entity1 instanceof HostileEntity != entity2 instanceof HostileEntity) return false;
        return true;
    }
}
