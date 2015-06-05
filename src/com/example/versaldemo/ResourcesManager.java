package com.example.versaldemo;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Typeface;


public class ResourcesManager
{
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    
    private static final ResourcesManager INSTANCE = new ResourcesManager();
    
    public Engine engine;
    public GameActivity activity;
    public BoundCamera camera;
    public VertexBufferObjectManager vbom;
    private Music mMusic;
    public Sound mButton;
    public Font mFont;
    private Music mBackground;
    private Sound mDice;
    
    //---------------------------------------------
    // TEXTURES & TEXTURE REGIONS
    //---------------------------------------------
   public ITextureRegion splash_region;
   private BitmapTextureAtlas splashTextureAtlas;
   public ITextureRegion menu_background_region;
   public ITextureRegion play_region;
   public ITextureRegion options_region;
   private BuildableBitmapTextureAtlas menuTextureAtlas;
   public BitmapTextureAtlas mFontTexture;
   public BuildableBitmapTextureAtlas gameTextureAtlas;
   public BuildableBitmapTextureAtlas game1TextureAtlas;
   public ITiledTextureRegion dicemini_region[]=new ITiledTextureRegion[3];
   public ITiledTextureRegion dice_region[]= new ITiledTextureRegion[6];
   public ITiledTextureRegion holder_region;
   public ITextureRegion rollbutton_region;
   public ITextureRegion background_region;
   public ITextureRegion dice1_region;
  
    //---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------

    public void loadMenuResources()
    {
        loadMenuGraphics();
        loadMenuAudio();
        loadMenuFonts();
    }
    
    private void loadMenuFonts()
    {
    	mFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
    			 
    	mFont = FontFactory.createStroke(activity.getFontManager(), mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),60, true, org.andengine.util.adt.color.Color.WHITE_ABGR_PACKED_INT, 3, org.andengine.util.adt.color.Color.BLACK_ABGR_PACKED_INT);
    			 
    	mFont.load();
    }

	public void loadGameResources()
    {
        loadGameGraphics();
       loadGameFonts();
        loadGameAudio();
    }
    
    private void loadMenuGraphics()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
    	menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1920, 1080, TextureOptions.BILINEAR);
    	menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "gamelogo.png");
    	play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "drunken.png");
    	options_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "liar.png");
    	
    	 BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
 	    gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(),2500, 1700, TextureOptions.BILINEAR);
 	 
 	dice_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice1.png", 7, 1);
 	dice_region[1] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice2.png", 7, 1);
 	dice_region[2] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice3.png", 7, 1);
 	dice_region[3] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice4.png", 7, 1);
 	dice_region[4] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice5.png", 7, 1);
 	dice_region[5] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice6.png", 7, 1);
    	
    	       
    	try 
    	{
    	    this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.menuTextureAtlas.load();
    	    this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.gameTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    	        Debug.e(e);
    	}
    }
    
    private void loadMenuAudio()
    {
    	try{
	    	mMusic = MusicFactory.createMusicFromAsset(activity.getMusicManager(),activity,"mfx/Beast.mp3");
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    	
    	try{
	    	mButton = SoundFactory.createSoundFromAsset(activity.getSoundManager(),activity,"mfx/button.mp3");
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    }

    private void loadGameGraphics()
    
    {
    	 BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
    	gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(),2500, 1700, TextureOptions.BILINEAR);
    	dice_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice1.png", 7, 1);
    	dice_region[1] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice2.png", 7, 1);
    	dice_region[2] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice3.png", 7, 1);
    	dice_region[3] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice4.png", 7, 1);
    	dice_region[4] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice5.png", 7, 1);
    	dice_region[5] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dice6.png", 7, 1);
    	holder_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "holder.png", 9, 1);
    	dicemini_region[0]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dicemini1.png", 7, 1);
    	dicemini_region[1] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dicemini2.png", 7, 1);
    	dicemini_region[2] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "dicemini3.png", 7, 1);
    	background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "dicebowl.png");
    	dice1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "dicebowl1.png");
    	rollbutton_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "rollbutton.png");
    	 
    	    try 
    	    {
    	        this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	        this.gameTextureAtlas.load();
    	    
    	    } 
    	    catch (final TextureAtlasBuilderException e)
    	    {
    	        Debug.e(e);
    	    }
    	}
    
    
    private void loadGameFonts()
    {
    	mFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		 
    	mFont = FontFactory.createStroke(activity.getFontManager(), mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD),60, true, org.andengine.util.adt.color.Color.WHITE_ABGR_PACKED_INT, 3, org.andengine.util.adt.color.Color.BLACK_ABGR_PACKED_INT);
    			 
    	mFont.load();
    }
    
    private void loadGameAudio()
    {
    	try{
	    	mBackground = MusicFactory.createMusicFromAsset(activity.getMusicManager(),activity,"mfx/Background.mp3");
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	   
	    }
    	try{
	    	mDice = SoundFactory.createSoundFromAsset(activity.getSoundManager(),activity,"mfx/dice_noise.mp3");
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    	
    	try{
	    	mButton = SoundFactory.createSoundFromAsset(activity.getSoundManager(),activity,"mfx/button.mp3");
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
    }
    
    
    
    public void loadSplashScreen()
    {
    	//BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx");
    	splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1920,1080, TextureOptions.BILINEAR);
    	splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "gfx/menu/Versal.png", 0, 0);
    	splashTextureAtlas.load();
   
    }
    
    public void unloadSplashScreen()
    {
    	splashTextureAtlas.unload();
    	splash_region = null;
    }
    
    public void musicplay(){
    	mMusic.play();
    }
    public void musicstop(){
    	mMusic.stop();
    }
    public void buttonplay(){
    	
    	mButton.play();
    }
    public void backgroundplay(){
    	mBackground.play();
    }
    public void backgroundstop(){
    	mBackground.stop();
    }
    public void diceplay(){
    	mDice.play();
    }
    /**
     * @param engine
     * @param activity
     * @param camera
     * @param vbom
     * <br><br>
     * We use this method at beginning of game loading, to prepare Resources Manager properly,
     * setting all needed parameters, so we can latter access them from different classes (eg. scenes)
     */
    public static void prepareManager(Engine engine, GameActivity activity, BoundCamera camera, VertexBufferObjectManager vbom)
    {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
    public void unloadMenuTextures()
    {
        menuTextureAtlas.unload();
    }
        
    public void loadMenuTextures()
    {
        menuTextureAtlas.load();
    }
    
    public void unloadGameTextures()
    {
    	//gameTextureAtlas.unload();
    }
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static ResourcesManager getInstance()
    {
        return INSTANCE;
    }
}
