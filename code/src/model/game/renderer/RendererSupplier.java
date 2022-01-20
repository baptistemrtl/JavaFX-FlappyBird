package model.game.renderer;

/**
 * Interface fonctionnelle qui sert de point d'extensibilité de gestionnaires du rendu sur des Elements dans la vue
 */
@FunctionalInterface
public interface RendererSupplier {

    /**
     * Create renderer renderer.
     *
     * @return the renderer
     */
    Renderer createRenderer();
}

