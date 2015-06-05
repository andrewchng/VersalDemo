package com.example.versaldemo;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.math.MathUtils;

import android.app.AlertDialog;
import android.widget.Toast;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
public class Dice extends AnimatedSprite
{
	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------
	    
	private Body body;
	public int value;
	
	int diceFrame;
	boolean diceStatus=false;
	int diceScore;
	

	
    // ---------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------
    
    public Dice(float pX, float pY, VertexBufferObjectManager vbo, Camera camera, PhysicsWorld physicsWorld,int x)
    {	
        super(pX, pY, ResourcesManager.getInstance().dice_region[x], vbo);
        createPhysics(camera, physicsWorld);   
    }
      
    private void createPhysics(final Camera camera, PhysicsWorld physicsWorld)

    {        
        body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(100,0.05f,300f));
        body.setUserData("player");
       physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, true)
        {
            @Override
            public void onUpdate(float pSecondsElapsed)
            {
                super.onUpdate(pSecondsElapsed);
                camera.onUpdate(0.1f);
            }
        });
    }
    

    // ---------------------------------------------
    // METHODS
    // ---------------------------------------------
    
    public void roll()
    {
    	mTimerHandler.reset();
    	unregisterUpdateHandler(mTimerHandler);
        final long[] PLAYER_ANIMATE = new long[] { 100, 100, 100,100, 100, 100,100 };      
        registerUpdateHandler(mTimerHandler);
        animate(PLAYER_ANIMATE, 0,6, true);
        diceStatus = true; //dice is moving
        int randomSpeed =MathUtils.random(-80,80);
        body.setLinearVelocity(new Vector2(randomSpeed, randomSpeed));  
        
    }
    
    public void stopRoll()
    {  
    	diceFrame =MathUtils.random(0,6);
        this.stopAnimation(diceFrame);  
        DrunkenDiceScene.addToScore(diceFrame);
       
        
       diceStatus = false;
       
    }
    
    public int getDiceFrame(){
    	return diceFrame;
    }
    
    TimerHandler mTimerHandler = new TimerHandler(MathUtils.random(2,3), true, new ITimerCallback() {
    	@Override
    	public void onTimePassed(TimerHandler pTimerHandler) {
    	 pTimerHandler.reset();	  		
    	 unregisterUpdateHandler(pTimerHandler);	
    	 stopRoll();
    	
    	}
});
    
    
}
