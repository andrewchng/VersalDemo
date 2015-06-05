package com.example.versaldemo;



import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.math.MathUtils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
public class DiceMini extends AnimatedSprite
{
	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------
	    
	private Body body;
	private boolean canRun = false;
	public void onDie() {
	}
	private int footContacts = 0;
	public int value;
	int value3=MathUtils.random(15,25);
	int TIME_REMAINING = value3;
	int diceNumber;
	
    // ---------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------
    
    public DiceMini(float pX, float pY, VertexBufferObjectManager vbo, Camera camera, PhysicsWorld physicsWorld,int x)
    {	
        super(pX, pY, ResourcesManager.getInstance().dicemini_region[x], vbo);
        createPhysics(camera, physicsWorld);
        //camera.setChaseEntity(this);
        diceNumber = x;
    }
    
    
    
    private void createPhysics(final Camera camera, PhysicsWorld physicsWorld)
    {        
        body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(100,0.05f,300f));

        body.setUserData("player");
       
        
    }
    public void roll()
    {
        canRun = true;
            
        
        
        //animate(PLAYER_ANIMATE, 0,6, true);
        value = MathUtils.random(0, 6);
        this.setCurrentTileIndex(value);
        //int value2 =MathUtils.random(-80,80);
        //body.setLinearVelocity(new Vector2(value2, value2)); 
    }
    public void jump()
    {
    	
    	 value = MathUtils.random(1, 6);
         this.stopAnimation(value);
       
    	
    }
    
   
}
