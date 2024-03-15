package org.nsgg.core.entity.collisions;

import org.nsgg.core.entity.terrain.Terrain;

public interface ICollisionTracker {

    public void update();
    public void add(Collidable collidable);
    public void remove(Collidable collidable);
}
