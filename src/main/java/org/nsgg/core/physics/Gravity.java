package org.nsgg.core.physics;

import org.nsgg.core.entity.collisions.Collidable;
import org.nsgg.core.entity.collisions.ICollisionTracker;

import java.util.List;

public class Gravity implements ICollisionTracker {

    private List<Collidable> toProcess;


    @Override
    public void update() {
        for (Collidable collidable : toProcess) {
            if(collidable.onGround) {continue;}

            //collidable.vy += -9.81f *
        }
    }

    @Override
    public void add(Collidable collidable) {
        toProcess.add(collidable);
    }

    @Override
    public void remove(Collidable collidable) {
        toProcess.remove(collidable);
    }
}
