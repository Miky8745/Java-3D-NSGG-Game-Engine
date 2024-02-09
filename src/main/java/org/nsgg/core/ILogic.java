package org.nsgg.core;

public interface ILogic {

    void init() throws Exception;

    void input();

    void update(float interval, MouseInput input);

    void render();

    void cleanup();
}
