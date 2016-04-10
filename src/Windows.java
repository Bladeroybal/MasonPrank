import java.util.HashMap;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class Windows {
    public static void mouseLR(long argument) {

        //SPI_SETMOUSEBUTTONSWAP using Windows SystemParametersInfo
        //Swaps or restores the meaning of the left and right mouse buttons

        //not required as not changing background
//        String path = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg";

        SPI.INSTANCE.SystemParametersInfo(
//                new UINT_PTR(SPI.SPI_SETDESKWALLPAPER),
                new UINT_PTR(SPI.SPI_SETMOUSEBUTTONSWAP),
                //0 if false, 1 if true
                new UINT_PTR(argument),
                "null",
                new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
    }

    public interface SPI extends StdCallLibrary {

        //from MSDN article
        //not using because not changing background
//        long SPI_SETDESKWALLPAPER = 20;

        long SPI_SETMOUSEBUTTONSWAP = 0x0021; //hex 0x0021
        long SPIF_UPDATEINIFILE = 0x01;
        long SPIF_SENDWININICHANGE = 0x02;

        SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
            {
                put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
                put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
            }
        });

        boolean SystemParametersInfo(
                UINT_PTR uiAction,
                UINT_PTR uiParam,
                String pvParam,
                UINT_PTR fWinIni
        );
    }
}