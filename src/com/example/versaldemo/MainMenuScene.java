package com.example.versaldemo;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.color.Color;

import com.example.versaldemo.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener{

	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		  switch(pMenuItem.getID())
	        {
	        case MENU_PLAY:
	        	 //Load Game Scene!
	            SceneManager.getInstance().loadDrunkenDiceScene(engine);
	            ResourcesManager.getInstance().buttonplay();
	           // ResourcesManager.getInstance().musicstop();
	            
	            return true;
	        case MENU_OPTIONS:
	        	
	        	 ResourcesManager.getInstance().buttonplay();
	        	 SceneManager.getInstance().loadLiarDiceScene(engine);
	            return true;
	        default:
	            return false;
	    }
	}

	@Override
	public void createScene() {
		//setBackground(new Background(4f/255f,173f/255f,82f/255f));
		 createBackground();
		// ResourcesManager.getInstance().musicplay();
		 createMenuChildScene();
		getBackground().setColor(Color.WHITE);
		// attachChild(new Text(0,0, resourcesManager.mFont, "Loading...", vbom));
	}

	@Override
	public void onBackKeyPressed() {
		 ResourcesManager.getInstance().buttonplay();
		 System.exit(0);
		 
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}
	private void createBackground()

	{
		setBackground(new Background(2f/255f,56f/255f,32f/255f));
	    attachChild(new Sprite(960,740, resourcesManager.menu_background_region, vbom)
	    {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) 
	        {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}
	private void createMenuChildScene()
	{
	    menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition(960,540);
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.play_region, vbom), 1.2f, 1);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.options_region, vbom), 1.2f, 1);
	    
	    menuChildScene.addMenuItem(playMenuItem);
	    menuChildScene.addMenuItem(optionsMenuItem);	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
	    
	    playMenuItem.setPosition(0,-100);
	    optionsMenuItem.setPosition(0,-300);
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(menuChildScene);
	}
}//

	
     