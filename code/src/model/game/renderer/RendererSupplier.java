package model.game.renderer;

/**
 * Interface fonctionnelle qui sert de point d'expensibilité de gestionnaires du rendu sur des Elements dans la vue
 */
@FunctionalInterface
public interface RendererSupplier {

    Renderer createRenderer();
}

