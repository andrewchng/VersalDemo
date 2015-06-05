package com.example.versaldemo;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.view.MotionEvent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.example.versaldemo.SceneManager.SceneType;
//LIAR DICE GAME SCENE
public class LiarDice extends BaseScene{
	public DiceMini[] player1;
	public DiceMini[] player2;
	public DiceMini[] player3;
	public DiceMini[] player4;
	public DiceMini[] player5;
	private PhysicsWorld physicsWorld;
	int player =1 ;
	@Override
	public void createScene() {
		createBackground();
		createPhysics();
	}
	public void deleteplayer(){
		
	}

	public void addplayer() {
		
		switch(player){
		case 1:
			player1=createDiceSet(1560,210);
		    player=2;
		 break;
		case 2:
			player2=createDiceSet(300,210);
			player=3;
		 break;
		case 3:
			player3=createDiceSet(600,210);
			player=4;
		 break;
		case 4:
			player4=createDiceSet(900,210);
			player=5;
		 break;
		case 5:
			player5=createDiceSet(1200,210);
			player =0;
		 break;
		}
		
	}

	public DiceMini[] createDiceSet(int x, int y) {	
	  
		final DiceMini[] dice=createdice(x,y);
	
	   final AnimatedSprite holder1 = new AnimatedSprite(x, y+190, resourcesManager.holder_region, vbom){
	        	int i;
	        	@Override
				public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					switch(pSceneTouchEvent.getAction()) {
					 case TouchEvent.ACTION_DOWN:
						 for(i=0;i<=8;i++){
							 this.setCurrentTileIndex(i);
						 }
					     break;
					 case TouchEvent.ACTION_UP:
						 this.setCurrentTileIndex(0);
						
					     break;
					 case TouchEvent.ACTION_MOVE:
						 
					     break;
					     }
					return true;
	        	}
	        };
	        registerTouchArea(holder1);
	    	setTouchAreaBindingOnActionDownEnabled(true);
	       
	
	Sprite dicebowl1 = new Sprite(x,y,resourcesManager.dice1_region,vbom) { 
		@Override
		public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			switch(pSceneTouchEvent.getAction()) {
			 case TouchEvent.ACTION_DOWN:
			     this.setScale(1.7f);
			     break;
			 case TouchEvent.ACTION_UP:
			     this.setScale(1.5f);
			     break;
			 case TouchEvent.ACTION_MOVE:
				
			 if (this.getY() < 540 && this.getX() <1440 && this.getX()>480){
						holder1.setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2  , pSceneTouchEvent.getY() - this.getHeight() / 2+170);
					    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[0].setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[1].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2+50  , pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[2].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2-50  , pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[3].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2+25  , pSceneTouchEvent.getY() - this.getHeight() / 2-50);
					    dice[4].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2-25  , pSceneTouchEvent.getY() - this.getHeight() / 2-50);
					    this.setRotation(360);
					    holder1.setRotation(360);	
			 }
			 else if (this.getY()>540 && this.getX() <1440 && this.getX()>480){
					    holder1.setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2  , pSceneTouchEvent.getY() - this.getHeight() / 2-170);
					    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[0].setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[1].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2+50  , pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[2].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2-50  , pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[3].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2+25  , pSceneTouchEvent.getY() - this.getHeight() / 2+50);
					    dice[4].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2-25  , pSceneTouchEvent.getY() - this.getHeight() / 2+50);
					    this.setRotation(180);
					    holder1.setRotation(180);
				    }
			 
			 else if (this.getX() <480 ){	
					    this.setRotation(90);
					    holder1.setRotation(90);
					    for(int x=0;x<5;x++)dice[x].setRotation(90);
					    holder1.setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2 +170 , pSceneTouchEvent.getY() - this.getHeight() / 2);
					    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[0].setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[1].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2  , pSceneTouchEvent.getY() - this.getHeight() / 2+50);
					    dice[2].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2  , pSceneTouchEvent.getY() - this.getHeight() / 2-50);
					    dice[3].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2-50  , pSceneTouchEvent.getY() - this.getHeight() / 2+25);
					    dice[4].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2-50  , pSceneTouchEvent.getY() - this.getHeight() / 2-25);
			 }    
			 
			 else if ( this.getX() >1440 ){	
					    this.setRotation(270);
					    holder1.setRotation(270);
					    for(int x=0;x<5;x++)dice[x].setRotation(270);
					    holder1.setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2 -170 , pSceneTouchEvent.getY() - this.getHeight() / 2);
					    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					    dice[0].setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
			            dice[1].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2  , pSceneTouchEvent.getY() - this.getHeight() / 2+50);
				        dice[2].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2  , pSceneTouchEvent.getY() - this.getHeight() / 2-50);
				        dice[3].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2+50  , pSceneTouchEvent.getY() - this.getHeight() / 2+25);
					    dice[4].setPosition(pSceneTouchEvent.getX() - this.getWidth()/ 2+50 , pSceneTouchEvent.getY() - this.getHeight() / 2-25);
			 }    	
			     break;
			     }
			return true;
		}
	};
	dicebowl1.setScale(1.5f);
	registerTouchArea(dicebowl1);
	setTouchAreaBindingOnActionDownEnabled(true);
	attachChild(dicebowl1);
	attachChild(holder1); 
	
	return dice;
}
	
//=====================================================================================================================================================	

private DiceMini[] createdice(int x,int y) {
	
    final DiceMini[] dicemini = new DiceMini[5];
	dicemini[0]= new DiceMini(x,y+20, vbom, camera, physicsWorld,2);
	dicemini[1] = new DiceMini(x+50,y+20, vbom, camera, physicsWorld,2);
	dicemini[2]= new DiceMini(x-50,y+20, vbom, camera, physicsWorld,2);
	dicemini[3] = new DiceMini(x+25,y-30, vbom, camera, physicsWorld,2);
    dicemini[4]= new DiceMini(x-25,y-30, vbom, camera, physicsWorld,2);
    for(int p=0;p<5;p++)attachChild(dicemini[p]);
	return dicemini;
		
	}

//====================================================================================================================================================


//=====================================================================================================================================================	



	public void createBackground()
	{	 
		 
		Sprite rollbutton = new Sprite(960, 540, resourcesManager.rollbutton_region, vbom){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				 case TouchEvent.ACTION_DOWN:
					
					
					if (player1!=null)
						for(int x=0;x<5;x++){player1[x].roll();}
					if (player2!=null)
						for(int x=0;x<5;x++){player2[x].roll();}
					if (player3!=null)
						for(int x=0;x<5;x++){player3[x].roll();}
					if (player4!=null)
						for(int x=0;x<5;x++){player4[x].roll();}
					if (player5!=null)
						for(int x=0;x<5;x++){player5[x].roll();}
						
					
					 
					 ResourcesManager.getInstance().diceplay();
					 this.setScale(1.2f);
				     break;
				     
				 case TouchEvent.ACTION_UP:
					 this.setScale(1f);
				     break;
				     
				 case TouchEvent.ACTION_MOVE:
					
					
					 
				     break;
				     }

				
				return true;
        	}
        
		};
		
		Sprite rollbutton1 = new Sprite(100, 540, resourcesManager.rollbutton_region, vbom){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				 case TouchEvent.ACTION_DOWN:
					 
					addplayer();
					 
					 this.setScale(1.2f);
				     break;
				 case TouchEvent.ACTION_UP:
					 this.setScale(1f);
				     break;
				 case TouchEvent.ACTION_MOVE:
					
					
					 
				     break;
				     }

				
				return true;
        	}
        
		};
		
		Sprite rollbutton2 = new Sprite(1350, 540, resourcesManager.rollbutton_region, vbom){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				 case TouchEvent.ACTION_DOWN:
					 SceneManager.getInstance().loadLiarDiceScene(engine);
					 
					 
					 this.setScale(1.2f);
				     break;
				 case TouchEvent.ACTION_UP:
					 this.setScale(1f);
				     break;
				 case TouchEvent.ACTION_MOVE:
					
					
					 
				     break;
				     }

				
				return true;
        	}
        
		};
		registerTouchArea(rollbutton1);
		registerTouchArea(rollbutton);
		registerTouchArea(rollbutton2);
		setTouchAreaBindingOnActionDownEnabled(true);  
	     attachChild(rollbutton);
	     attachChild(rollbutton1);
	     attachChild(rollbutton2);
	    setBackground(new Background(2f/255f,56f/255f,32f/255f));
	}


	@Override
	public void onBackKeyPressed() {
		
		 SceneManager.getInstance().loadMenuScene1(engine);
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_LIARDICE;
	}

	@Override
	public void disposeScene() {
		  camera.setCenter(960, 540);
		
	}


private void createPhysics()
{
    physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0,-5), false); 
    registerUpdateHandler(physicsWorld);
}

}

	