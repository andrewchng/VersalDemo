package com.example.versaldemo;

import java.io.IOException;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.SAXUtils;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelLoader;
import org.andengine.util.math.MathUtils;
import org.xml.sax.Attributes;

import android.app.AlertDialog;
import android.content.Context;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.example.versaldemo.SceneManager.SceneType;

public class DrunkenDiceScene extends BaseScene implements  IOnSceneTouchListener {
	private HUD gameHUD;
	
	private static Text scoreText;
	private static Text resultText;
	public static int score = 0;
	private PhysicsWorld physicsWorld;
	public static boolean onTouch = false; 
	public VertexBufferObjectManager vbom;	
	private Dice[] rolldice = createDice();
	public static int totalScore;
	//public AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
	
	
	
	
	@Override
	public void createScene() {
		
		createBackground();
		ResourcesManager.getInstance().backgroundplay();
		createPhysics();	
		setOnSceneTouchListener(this);
		createWall();	
		createHUD();
	}
	
	private Dice[] createDice(){
		Dice[] player=new Dice[2];
		player[0]= new Dice(800,640, vbom, camera, physicsWorld,0);
		player[1] = new Dice(800,440, vbom, camera, physicsWorld,0);
		//player[2]= new Dice(900,540, vbom, camera, physicsWorld,0);
		//player[3] = new Dice(1000,440, vbom, camera, physicsWorld,0);
	    //player[4]= new Dice(1100,540, vbom, camera, physicsWorld,0);
	    for(int p=0;p<2;p++)attachChild(player[p]);
		return player;
	}
	
	private void createWall() {
		
		Rectangle wall[]=new Rectangle[8];
		FixtureDef WALL_FIX = PhysicsFactory.createFixtureDef(100.0f, 0.0f,300.0f);
		
		wall[0] = new Rectangle(660f, 270f, 320f, 0.0000001f, vbom);
        wall[1] = new Rectangle(1240f,270f,320f, 0.0000001f, vbom);
        wall[2] = new Rectangle(545f, 570f, 0.0000001f,350f, vbom);
        wall[3] = new Rectangle(1350f,570f,  0.0000001f,350f, vbom);
        wall[4] = new Rectangle(950f, 950f, 320f, 0.0000001f, vbom);
        wall[5] = new Rectangle(950f, 150f, 320f, 0.0000001f, vbom);
        wall[6] = new Rectangle(660f,830f, 320f, 0.0000001f, vbom);
        wall[7] = new Rectangle(1240f, 830f, 320f, 0.0000001f, vbom);

        for(int x=0;x<8;x++){
        	//rotate the 'ghost' wall
        	 if(x==0|x==7)wall[x].setRotation(-45);
        	 else if(x==6|x==1)wall[x].setRotation(45);
        	 PhysicsFactory.createBoxBody(physicsWorld, wall[x], BodyType.StaticBody, WALL_FIX);
        	 attachChild(wall[x]);
        }

		
	}

	@Override
	public void onBackKeyPressed() {
		ResourcesManager.getInstance().backgroundstop();
		 SceneManager.getInstance().loadMenuScene(engine);	
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disposeScene() {
		camera.setHUD(null);
	    camera.setCenter(960, 540);
	    camera.setChaseEntity(null);
	    // TODO code responsible for disposing scene
	    // removing all game scene objects.
		}
	
	private void createBackground()
	    {
		    Sprite bgSprite = new Sprite(960,540,ResourcesManager.getInstance().background_region,vbom);
		    setBackground(new Background(2f/255f,56f/255f,32f/255f));
		    attachChild(bgSprite);
		}
	
	private void createHUD()
	{
	    gameHUD = new HUD();

	    // CREATE SCORE TEXT
	   scoreText = new Text(0, 1000, resourcesManager.mFont, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
	   scoreText.setAnchorCenter(0, 0);    
	   scoreText.setText("Score: 0");
	
	   
	  //CREATE RESULT TEXT 
	  resultText = new Text(600, 540, resourcesManager.mFont, "this text is too long or is it?why is this so important? make it longer", new TextOptions(HorizontalAlign.CENTER), vbom);
	  resultText.setAnchorCenter(0, 0);
	  resultText.setText("");
	  gameHUD.attachChild(scoreText);
	  gameHUD.attachChild(resultText);
	  camera.setHUD(gameHUD);
	
	}
	
	public static void addToScore(int i)
	{
		int x = 0;
		switch(i){
		case 0:
			x=4;
			break;
		case 1:
			x=2;
			break;
		case 2:
			x=3;
			break;
		case 3:
			x=4;
			break;
		case 4:
			x=5;
			break;
		case 5:
			x=6;
			break;
		case 6:
			x=1;	
		}
	    score += x;
	    totalScore = score;
	   
	    scoreText.setText("Score: " + score);
	   
	  
}
	
	public static int getScore(){
		return score;
	}
	
	public void showResult(int c){
		
		boolean playOrder = true; //clockwise = true , anti-clockwise = false;
		
		switch(c){
		
		case 1:
			 resultText.setText("HAHAHA");
			 break;
			 
		case 2:
			 if(rolldice[0].diceFrame == rolldice[1].diceFrame)
				 resultText.setText("Play order changed!");
			 	
			 else	
				 resultText.setText("Next Player!");
			 break;
			 
		case 3:
			resultText.setText("Next Player!");
			 break;
			 
		case 4:
			if(rolldice[0].getDiceFrame() == rolldice[1].getDiceFrame())
				resultText.setText("Play order changed!");
			 else	
				 resultText.setText("Next Player!");
			break;
			
		case 5:
			resultText.setText("Next Player!");
			 break;
			 
		case 6:
			if(rolldice[0].getDiceFrame() == rolldice[1].getDiceFrame())
				resultText.setText("Play order changed!");
			 else	
				 resultText.setText("Next Player!");
			break;
			
		case 7:
			 resultText.setText("Pour in any amount of beer you want! ");
			 break;
			 
			 
		case 8:
			 if(rolldice[0].getDiceFrame() == rolldice[1].getDiceFrame())
				 resultText.setText("Drink 1/2 the beer on the table and roll again!");
			 else if(rolldice[0].getDiceFrame() == 0 && rolldice[1].getDiceFrame() == 3)
				 resultText.setText("Drink 1/2 the beer on the table and roll again!");
			 else if(rolldice[0].getDiceFrame() == 3 && rolldice[1].getDiceFrame() == 1)
				 resultText.setText("Drink 1/2 the beer on the table and roll again!");
			 else	
				 resultText.setText("Drink 1/2 the beer on the on the table!");
			break;
			
		case 9:
			 resultText.setText("Drink ALL the beer on the table!!");
			 break;
			 
		case 10:
			 if(rolldice[0].getDiceFrame() == rolldice[1].getDiceFrame())
				 resultText.setText("Play order changed!");
			 else 
				 resultText.setText("Next Player!");
			break;
			
		case 11:
			 resultText.setText("Next Player!");
			 break;
			 
		case 12:
			if(rolldice[0].getDiceFrame() == rolldice[1].getDiceFrame())
				resultText.setText("Play order changed!");
			 else	
				 resultText.setText("Next Player!");
			break;
	
		}
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.isActionDown())
	    {
			resultText.setText("");
			    xTimerHandler.reset();	  		
	    	    unregisterUpdateHandler(xTimerHandler);
	        	ResourcesManager.getInstance().diceplay();
	        	for(int x=0;x<2;x++)rolldice[x].roll(); 
	        	registerUpdateHandler(xTimerHandler);
	        	score = 0;
			
	    }
	    
	    return false;
	}
	
	private void createPhysics()
	{
	    physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, 0), false); 
	    registerUpdateHandler(physicsWorld);
	}
	 TimerHandler xTimerHandler = new TimerHandler(3.1f, true, new ITimerCallback() {
	    	@Override
	    	public void onTimePassed(TimerHandler pTimerHandler) {
	    	 pTimerHandler.reset();	  		
	    	 unregisterUpdateHandler(pTimerHandler);	
	    	 showResult(score);
	    	
	    	}
	 });
	
}