package es.july.killthemall;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



@SuppressLint("ViewConstructor")
public class GameView extends SurfaceView {

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private Bitmap bmpBlood;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	private List<TempSprite> temps = new ArrayList<TempSprite>();
	private long lastClick;
	private MediaPlayer mp = null;
	private MediaPlayer fin = null;
	private Vibrator vibrar = null;
	private Marcador marcador;
	private Paint paint;
	private int malos;

	public GameView(Context context, int puntos, int malos) {
		super(context);
		this.malos=malos;
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();

		holder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				
				boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                	try {                		
                 	    gameLoopThread.join();
                 	    fin.stop();
                        retry = false;
                     } catch (InterruptedException e) {}
                }
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				createSprites();				
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
		
		marcador = new Marcador(puntos);
		bmpBlood = BitmapFactory.decodeResource(getResources(), R.drawable.blood1);
		mp = MediaPlayer.create(context, R.raw.sonido);
		fin = MediaPlayer.create(context, R.raw.fin);
		vibrar = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
		paint = new Paint();
			paint.setColor(Color.WHITE);
			paint.setTextAlign(Align.LEFT);
			paint.setTypeface(Typeface.createFromAsset(this.getContext().getAssets(),
			         "fonts/KellySlab-Regular.ttf"));
			paint.setTextSize(30);
			paint.setAntiAlias(true);
	}
	
	private void createSprites() {
		int[] spritesMalos={R.drawable.bad1,R.drawable.bad2,R.drawable.bad3,R.drawable.bad4,R.drawable.bad5
				,R.drawable.bad6,R.drawable.good1,R.drawable.good2,R.drawable.good3,R.drawable.good4,
				R.drawable.good5,R.drawable.good6};
		for(int i=0;i<malos;i++){
	        sprites.add(createSprite(spritesMalos[i%12]));
		}
	}

	private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);
	}

	
	
	@SuppressLint("WrongCall")
	protected void onDraw(Canvas canvas) {
		if(canvas !=null){
			paint.setColor(Color.WHITE);				   
			//drawCenterLine(canvas, paint);
			canvas.drawColor(Color.BLACK);
			
			if(marcador.acabado())
				drawGana(canvas, paint);
			else
				drawMarcador(canvas, paint);
		
			for (int i = temps.size() - 1; i >= 0; i--) {
				temps.get(i).onDraw(canvas);
			}
			for (Sprite sprite : sprites) {
				sprite.onDraw(canvas);
			}
		}
	}
	
	// Pinta una raya en medio a lo PONG
	@SuppressWarnings("unused")
	private void drawCenterLine(Canvas canvas, Paint paint) {
		int w = 6;
		int h = 20;
		int gap = 10;
		int ini = gap / 2; // por estetica, si no seria 0

		for(int i = 0; i < this.getHeight() / (h+gap); i++) {
			canvas.drawRect(this.getWidth()/2 - w/2, ini,
					this.getWidth()/2 + w/2, ini + h, paint);
			ini += h + gap;
		}
	}
	
	// Pinta el marcador en el margen superior izquierdo
	private void drawMarcador(Canvas canvas, Paint paint) {
		canvas.drawText(Integer.toString(marcador.getPuntos()),3 , 30, paint);
	}
	
	// Pinto YOU WIN si ha ganado
	private void drawGana(Canvas canvas, Paint paint) {
		paint.setColor(Color.RED);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(60);
		canvas.drawText("YOU WIN",this.getWidth()/2 , this.getHeight()/2, paint);
	}
	
	// Me devuelve el marcador para pasarselo a la activity main
	public Marcador getMarcador() {
		return marcador;
	}
	
	// Me devuelve el numero de malos para pasarselo a la activity main
	public int getSprites(){
		return sprites.size();
	} 
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
          if (System.currentTimeMillis() - lastClick > 300) {
                 lastClick = System.currentTimeMillis();
                 float x = event.getX();
                 float y = event.getY();
                 synchronized (getHolder()) {
                     for (int i = sprites.size() - 1; i >= 0; i--) {
                         Sprite sprite = sprites.get(i);
                         if (sprite.isCollition(x, y)) {
                        	   if(KillthemOpciones.getInstance().soundEnabled())
                        		   mp.start();
                               sprites.remove(sprite);
                               vibrar.vibrate(50);
                               temps.add(new TempSprite(temps, this, x, y, bmpBlood));
                               marcador.addPuntos();
                               if(marcador.acabado())
                   					fin.start();
                               break;
                         }
                     }
                 }
          }
          return true;
    }
}
