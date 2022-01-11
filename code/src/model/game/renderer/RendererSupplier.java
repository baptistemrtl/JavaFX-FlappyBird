package model.game.renderer;

@FunctionalInterface
public interface RendererSupplier {

    Renderer createRenderer();
}

