public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static int counter = 0;
    static long time =0;


    public static void main(String[] args){
        float[] arr = new float[size];
        float[] firstPartArray = new float[h];
        float[] secondPartArray = new float[h];

        fillArray(arr);
        time = System.currentTimeMillis();
        trigonometricFunction(arr);
        System.out.println(System.currentTimeMillis() - time + " = 1  = ");

        fillArray(arr);

        time = System.currentTimeMillis();
        System.arraycopy(arr, 0, firstPartArray , 0, h);
        System.arraycopy(arr, h, secondPartArray , 0, h);



        Thread thread1 = new Thread(() -> {
            trigonometricFunction(firstPartArray);
            mergeArrays(firstPartArray, 0, arr, 0,  h);
        });

        Thread thread2 = new Thread(() -> {
            trigonometricFunction(secondPartArray);
            mergeArrays(secondPartArray, 0, arr, h,  h);
        });


        thread1.start();
        thread2.start();
    }

    static void fillArray(float[] myArray){
        for(int i=0; i<myArray.length; i++){
            myArray[i]=1;
        }
    }

    static void trigonometricFunction(float[] myArray){
        for(int i=0; i<myArray.length; i++){
            myArray[i] = (float)(myArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    static synchronized void mergeArrays(float[] srcArray, int  srcPos, float[] destArray, int destPos,  int length){
        System.arraycopy(srcArray, srcPos,  destArray, destPos, length);
        counter++;
        if(counter == 2){
            System.out.println((System.currentTimeMillis() - time) + " = 2  = ");
        }
    }

}
