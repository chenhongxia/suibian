package com.example.suishouji.base.utils;


import com.example.suishouji.base.BaseApplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;

/**
 * 屏幕显示相关工具类
 */
public class ScreenUtil
{
	private static float currentDensity = 0;
	private static float scaledDensity = 0;
	private final static int iconSize = 48;
	private final static int notification_height = 25;
	/**
	 * 大屏幕的高度
	 */
	private final static int LARDGE_SCREEN_HEIGHT = 960;
	/**
	 * 大屏幕的宽度
	 */
	final static int LARDGE_SCREEN_WIDTH = 720;
	private final static int M9_SCREEN_WIDTH = 640;

	public static final int SCREEN_TYPE_OTHER = -1;
	public static final int SCREEN_TYPE_HVGA = 0;
	public static final int SCREEN_TYPE_QVGA = 1;
	public static final int SCREEN_TYPE_WQVGA = 2;
	public static final int SCREEN_TYPE_WQVGA432 = 3;
	public static final int SCREEN_TYPE_WVGA = 4;
	public static final int SCREEN_TYPE_FWVGA = 5;
	public static final int SCREEN_TYPE_VGA = 6;
	public static final int SCREEN_TYPE_WSVGA_Tablet = 7;
	public static final int SCREEN_TYPE_WXGA_Tablet = 8;

	/**
	 * 获取状态栏高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getStatusbarHeight(Activity activity)
	{
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param mContext
	 * @return
	 */
	public static int getScreenHeight(Context mContext)
	{
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param mContext
	 * @return
	 */
	public static int getScreenWidth(Context mContext)
	{
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取手机屏幕类型
	 * 
	 * @param act
	 * @return 返回值对应类型-1:其它屏幕 0:HVGA 1:QVGA 2:WQVGA 400 3:WQVGA 432 4:WVGA 800
	 *         5:WVGA 854 6:VGA
	 */
	public static int getScreenType(Activity act)
	{
		int screenWidth,screenHeight;
		WindowManager windowManager = act.getWindowManager();
		Display display = windowManager.getDefaultDisplay();

		if (display.getWidth() > display.getHeight())
		{
			screenHeight = display.getWidth();
			screenWidth = display.getHeight();
		} else
		{
			screenWidth = display.getWidth();
			screenHeight = display.getHeight();
		}
		if (screenHeight == 640 && screenWidth == 480)// VGA
			return SCREEN_TYPE_VGA;
		else if (screenHeight == 854 && screenWidth == 480)// WVGA 854
			return SCREEN_TYPE_FWVGA;
		else if (screenHeight == 800 && screenWidth == 480)// WVGA
			return SCREEN_TYPE_WVGA;
		else if (screenHeight == 432 && screenWidth == 240)// WQVGA 432
			return SCREEN_TYPE_WQVGA432;
		else if (screenHeight == 400 && screenWidth == 240)// WQVGA
			return SCREEN_TYPE_WQVGA;
		else if (screenHeight == 320 && screenWidth == 240)// qvga
			return SCREEN_TYPE_QVGA;
		else if (screenHeight == 480 && screenWidth == 320)// HVGA
			return SCREEN_TYPE_HVGA;
		else if (screenHeight == 600 && screenWidth == 1024)
			return SCREEN_TYPE_WSVGA_Tablet;
		else if (screenHeight == 800 && screenWidth == 1280)
			return SCREEN_TYPE_WXGA_Tablet;

		return SCREEN_TYPE_OTHER;
	}

	public static float getScreenDensity(Context context)
	{
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return dm.density;
	}

	/**
	 * screen全屏是否需要全屏
	 * 
	 * @param screen
	 *            Activity界面
	 * @param enable
	 *            true=全屏,false=非全屏
	 */
	public static void fullScreen(Activity screen, boolean enable)
	{
		if (enable)
		{
			// go full screen
			WindowManager.LayoutParams attrs = screen.getWindow().getAttributes();
			// 添加全屏标志位到当前的flags
			attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
			screen.getWindow().setAttributes(attrs);
			screen.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		} else
		{
			// go non-full screen
			WindowManager.LayoutParams attrs = screen.getWindow().getAttributes();
			// 从当前的flags清除全屏标志位
			attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			screen.getWindow().setAttributes(attrs);
			screen.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}

	public static int getSizeOfDip(Context mContext, int dipValue)
	{
		return (int) (dipValue * getScreenDensity(mContext) + 0.5);
	}

	public static int getSizeOfDip(Context mContext, float dipValue)
	{
		return (int) (dipValue * getScreenDensity(mContext) + 0.5);
	}

	/**
	 * This method converts dp unit to equivalent pixels, depending on device
	 * density.
	 * 
	 * @param dp
	 *            A value in dp (density independent pixels) unit. Which we need
	 *            to convert into pixels
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on
	 *         device density
	 */
	public static float convertDpToPixel(float dp, Context context)
	{
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent
	 * pixels.
	 * 
	 * @param px
	 *            A value in px (pixels) unit. Which we need to convert into db
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context)
	{
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	/**
	 * 获取屏幕信息，如大小、屏幕密度、字体缩放等
	 * 
	 * @return DisplayMetrics
	 */
	public static DisplayMetrics getMetrics()
	{
		// Log.e("getMetrics", "getMetrics");
		DisplayMetrics metrics = new DisplayMetrics();
		Context ctx = BaseApplication.getInstance().getContext();
		if (ctx == null)
		{
			Log.e("ScreenUtil.getMetrics", "ApplicationContext is null!");
			return metrics;
		}
		final WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		final Display display = windowManager.getDefaultDisplay();
		display.getMetrics(metrics);
		boolean isPortrait = display.getWidth() < display.getHeight();
		final int width = isPortrait ? display.getWidth() : display.getHeight();
		final int height = isPortrait ? display.getHeight() : display.getWidth();
		metrics.widthPixels = width;
		metrics.heightPixels = height;
		return metrics;
	}

	/**
	 * 获取屏幕密度
	 * 
	 * @return float
	 */
	public static float getDensity()
	{
		DisplayMetrics metrics = getMetrics();
		return metrics.density;
	}

	public static float getDensity(Context context)
	{
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getApplicationContext().getResources().getDisplayMetrics();
		return dm.density;
	}

	/**
	 * 获取图标大小
	 * 
	 * @return int
	 */
	public static int getIconSize()
	{
		float density = getDensity();
		return (int) (iconSize * density);
	}

	/**
	 * 获取通知栏高度
	 * 
	 * @return int
	 */
	public static int getNotificationHeight()
	{
		return notification_height;
	}

	/**
	 * 获取壁纸高宽
	 * 
	 * @return int[]
	 */
	public static int[] getWallpaperWH()
	{
		int[] wh = getScreenWH();
		return new int[] { wh[0] * 2, wh[1] };
	}

	/**
	 * 重新计算cellLayout上单元格的高宽，防止因屏幕高度获取不对引发的错误
	 * 
	 * @return int[]
	 */
	public static int[] getScreenWH()
	{
		int[] screenWH = { 320, 480 };
		Context ctx = BaseApplication.getInstance().getContext();
		if (ctx == null)
		{
			Log.e("ScreenUtil.getScreenWH", "ApplicationContext is null!");
			return screenWH;
		}
		final WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		final Display display = windowManager.getDefaultDisplay();
		boolean isPortrait = display.getWidth() < display.getHeight();
		final int width = isPortrait ? display.getWidth() : display.getHeight();
		final int height = isPortrait ? display.getHeight() : display.getWidth();
		screenWH[0] = width;
		screenWH[1] = height;
		return screenWH;
	}

	/**
	 * 是否大屏幕
	 * 
	 * @return boolean
	 */
	public static boolean isLargeScreen()
	{
		int w = getScreenWH()[0];
		if (w >= 480)
			return true;
		else
			return false;
	}

	/**
	 * 是否是更大的屏幕 高度大于 960 宽度不等于640
	 * 
	 * @return boolean
	 */
	public static boolean isExLardgeScreen()
	{
		int[] wh = getScreenWH();
		// return wh[0] >= LARDGE_SCREEN_WIDTH && wh[1] >= LARDGE_SCREEN_HEIGHT;
		return wh[1] >= LARDGE_SCREEN_HEIGHT && wh[0] != M9_SCREEN_WIDTH;
	}

	/**
	 * 是否高清屏，宽度大于960
	 * 
	 * @return boolean
	 */
	public static boolean isSuperLargeScreen()
	{
		int[] wh = getScreenWH();
		return wh[0] > 960;
	}

	/**
	 * 是否小屏幕
	 * 
	 * @return boolean
	 */
	public static boolean isLowScreen()
	{
		int w = getScreenWH()[0];
		if (w < 320)
			return true;
		else
			return false;
	}

	/**
	 * 返回屏幕尺寸(宽)
	 * 
	 * @param context
	 * @return int
	 */
	public static int getCurrentScreenWidth(Context context)
	{
		DisplayMetrics metrics = getDisplayMetrics(context);
		boolean isLand = isOrientationLandscape(context);
		if (isLand)
		{
			return metrics.heightPixels;
		}
		return metrics.widthPixels;
	}

	/**
	 * 返回屏幕尺寸(高)
	 * 
	 * @param context
	 * @return int
	 */
	public static int getCurrentScreenHeight(Context context)
	{
		DisplayMetrics metrics = getDisplayMetrics(context);
		boolean isLand = isOrientationLandscape(context);
		if (isLand)
		{
			return metrics.widthPixels;
		}
		return metrics.heightPixels;
	}

	/**
	 * 返回屏幕尺寸
	 * 
	 * @param context
	 * @return DisplayMetrics
	 */
	public static DisplayMetrics getDisplayMetrics(Context context)
	{
		return context.getResources().getDisplayMetrics();
	}

	/**
	 * 判断是否横屏
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isOrientationLandscape(Context context)
	{
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			return true;
		}
		return false;
	}

	/**
	 * 获取view的bitmap<br>
	 * 在内存溢出的情况下，返回null
	 * 
	 * @param v
	 * @return Bitmap
	 */
	public static Bitmap getViewBitmap(View v)
	{
		v.clearFocus();
		v.setPressed(false);
		boolean willNotCache = v.willNotCacheDrawing();
		v.setWillNotCacheDrawing(false);
		// Reset the drawing cache background color to fully transparent
		// for the duration of this operation
		int color = v.getDrawingCacheBackgroundColor();
		v.setDrawingCacheBackgroundColor(0);
		if (color != 0)
		{
			v.destroyDrawingCache();
		}
		v.buildDrawingCache();
		Bitmap cacheBitmap = v.getDrawingCache();
		if (cacheBitmap == null)
		{
			// Log.e(Global.TAG, "failed getViewBitmap(" + v + ")", new
			// RuntimeException());
			return null;
		}
		Bitmap bitmap = null;
		try
		{
			bitmap = Bitmap.createBitmap(cacheBitmap);
		} catch (Throwable th)
		{
			th.printStackTrace();
		}
		// Restore the view
		v.destroyDrawingCache();
		v.setWillNotCacheDrawing(willNotCache);
		v.setDrawingCacheBackgroundColor(color);
		return bitmap;
	}

	/**
	 * 获取view的cache *
	 * 注意:此处仅获取view的缓存，没有create新的bitmap，所以建议不要调用Bitmap.recycle()显式回收
	 * 
	 * @param v
	 * @return Bitmap
	 */
	public static Bitmap getViewCache(View v)
	{
		Bitmap cacheBitmap = v.getDrawingCache();
		if (cacheBitmap != null)
			return cacheBitmap;
		v.clearFocus();
		v.setPressed(false);
		v.setWillNotCacheDrawing(false);
		v.setDrawingCacheEnabled(true);
		int color = v.getDrawingCacheBackgroundColor();
		if (color != 0)
		{
			v.destroyDrawingCache();
			v.setDrawingCacheBackgroundColor(0);
		}
		v.buildDrawingCache();
		cacheBitmap = v.getDrawingCache();
		if (cacheBitmap == null)
		{
			// Log.e(Global.TAG, "failed getViewBitmap(" + v + ")", new
			// RuntimeException());
			return null;
		}
		// Log.d(Global.TAG, "rebuild the cache");
		return cacheBitmap;
	}

	/**
	 * dp转px
	 * 
	 * @param context
	 * @param dipValue
	 * @return int
	 */
	public static int dip2px(Context context, float dipValue)
	{
		if (currentDensity > 0)
			return (int) (dipValue * currentDensity + 0.5f);
		currentDensity = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * currentDensity + 0.5f);
	}

	/**
	 * sp转px
	 * 
	 * @param context
	 * @param spValue
	 * @return int
	 */
	public static int sp2px(Context context, float spValue)
	{
		if (scaledDensity > 0)
			return (int) (spValue * scaledDensity + 0.5f);
		scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * scaledDensity + 0.5f);
	}

	/**
	 * This methode can be used to calculate the height and set it for views
	 * with wrap_content as height. This should be done before
	 * ExpandCollapseAnimation is created.
	 * 
	 * @param context
	 * @param view
	 */
	public static void setHeightForWrapContent(Context context, View view)
	{
		int screenWidth = getCurrentScreenWidth(context);
		int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(screenWidth, MeasureSpec.EXACTLY);
		view.measure(widthMeasureSpec, heightMeasureSpec);
		int height = view.getMeasuredHeight();
		view.getLayoutParams().height = height;
	}

	/**
	 * 获取屏幕尺寸
	 * 
	 * @author zhaoshiliang
	 * @data 2014-7-25
	 * @time 下午3:57:54
	 * @param context
	 * @return
	 */
	public static double getScreenInchDouble(Context context)
	{
		DisplayMetrics dm = getDisplayMetrics(context);
		double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
		double screenSize = diagonalPixels / (160 * dm.density);
		return screenSize;
	}

	/**
	 * 是否为平板
	 * 
	 * @author zhaoshiliang
	 * @data 2014-7-25
	 * @time 下午4:47:52
	 * @param context
	 * @return
	 */
	public static boolean isTablet(Context context)
	{
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * 判断是否为平板
	 * 
	 * @author zhaoshiliang
	 * @data 2014-7-25
	 * @time 下午5:00:08
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isPad(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		// 屏幕宽度
		float screenWidth = display.getWidth();
		// 屏幕高度
		float screenHeight = display.getHeight();
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
		double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
		// 屏幕尺寸
		double screenInches = Math.sqrt(x + y);
		// 大于6尺寸则为Pad
		if (screenInches >= 6.0)
		{
			return true;
		}
		return false;
	}
}
