package mars.all.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

 public static String getFirstString(String str,String regex,int index){
     if(null==str||null==regex||index<1){
         return "";
     }
     Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);//忽略大小写  换行符
     Matcher matcher = pattern.matcher(str); //匹配器
     
     while(matcher.find()){//是否有下一个
         return matcher.group(index); //反回第index个
     }
    return null;
     
 }

}
