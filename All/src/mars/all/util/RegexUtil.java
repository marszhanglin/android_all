package mars.all.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

 public static String getFirstString(String str,String regex,int index){
     if(null==str||null==regex||index<1){
         return "";
     }
     Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);//���Դ�Сд  ���з�
     Matcher matcher = pattern.matcher(str); //ƥ����
     
     while(matcher.find()){//�Ƿ�����һ��
         return matcher.group(index); //���ص�index��
     }
    return null;
     
 }

}
