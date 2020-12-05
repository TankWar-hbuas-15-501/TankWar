package foundation;

//  难易度类
public class Difficulty {
    //  定义难度枚举
    enum Diff {Easy, Normal, Hard}
    //             0    1       2
    //  定义游戏难度（默认简单）
    private static Diff diff= Diff.Easy;

    //  获得用户选择难度下标+1
    public static int diffIndex(Diff difficulty){
        //  将难度枚举转换成数组
        Diff[] difficulties = Diff.values();
        //  遍历所有难度
        for(int i=0;i<difficulties.length;i++)
            //  获得用户选择难度下标
            if(difficulties[i]==difficulty)
                return i+1;

        return 1;
    }
    //  设置难易度
    public static void setDiff(Diff di){
        diff=di;
    }
    //  获得难易度
    public static Diff getDiff(){
        return diff;
    }
}
