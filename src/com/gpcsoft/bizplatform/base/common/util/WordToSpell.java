package com.gpcsoft.bizplatform.base.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordToSpell {

	    //字母Z使用了两个标签，这里有２７个值
	    //i, u, v都不做声母, 跟随前面的字母
	    private char[] chartable =
	            {
	                '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',
	                '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然',
	                '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座'
	            };

	    private char[] alphatable =
	            {
	                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',

	                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	            };


	    private int[] table = new int[27];

	    //初始化
	    {
	        for (int i = 0; i < 27; ++i) {
	            table[i] = gbValue(chartable[i]);
	        }
	    }

	    public WordToSpell() {

	    }

	    //主函数,输入字符,得到他的声母,
	    //英文字母返回对应的大写字母
	    //其他非简体汉字返回 '0' edited 返回原字符

	    public char Char2Alpha(char ch) {

	        if (ch >= 'a' && ch <= 'z')
	            return (char) (ch - 'a' + 'A');
	        if (ch >= 'A' && ch <= 'Z')
	            return ch;


	        int gb = gbValue(ch);
	        if (gb < table[0])
	            return ch;


	        int i;
	        for (i = 0; i < 26; ++i) {
	            if (match(i, gb))
	                break;
	        }

	        if (i >= 26)
	            return ch;
	        else
	            return alphatable[i];
	    }

	    //根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
	    public String String2Alpha(String SourceStr) {
	        String Result = "";
	        int StrLength = SourceStr.length();
	        int i;
	        try {
	            for (i = 0; i < StrLength; i++) {
	                Result += Char2Alpha(SourceStr.charAt(i));
	            }
	        } catch (Exception e) {
	            Result = "";
	        }
	        return Result;
	    }

	    private boolean match(int i, int gb) {
	        if (gb < table[i])
	            return false;

	        int j = i + 1;

	        //字母Z使用了两个标签
	        while (j < 26 && (table[j] == table[i]))
	            ++j;

	        if (j == 26)
	            return gb <= table[j];
	        else
	            return gb < table[j];

	    }

	    //取出汉字的编码
	    private int gbValue(char ch) {
	        String str = new String();
	        str += ch;
	        try {
	            byte[] bytes = str.getBytes("GB2312");
	            if (bytes.length < 2)
	                return 0;
	            return (bytes[0] << 8 & 0xff00) + (bytes[1] &
	                    0xff);
	        } catch (Exception e) {
	            return 0;
	        }

	    }

	    public static void main(String[] args) {
	    	WordToSpell obj1 = new WordToSpell();
	        System.out.println(obj1.String2Alpha("测试：中华人民共和国！"));
	        System.out.println(obj1.String2Alpha("裴贺先"));
	        System.out.println(obj1.Char2Alpha('你'));
	        Connection con=null;
	        PreparedStatement stat = null;
	        PreparedStatement p2 = null;
	        ResultSet result=null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.10:1521:project", "pubservice", "pubservice");
	            stat = con.prepareStatement("select t.ID, t.CATEGORY_NAME from purcatalog_category t");
	            p2=con.prepareStatement("update purcatalog_category t2 set t2.short_spell_name=? where t2.ID=?");
	            result = stat.executeQuery();
	            while (result.next()) {
	            	String id = result.getString(1);
		             String row = result.getString(2);
		             if (row != null){
		            	 System.out.println(row);
		            	 p2.setString(1, String.valueOf(obj1.String2Alpha(row)));//去掉0（非简体汉字）
		            	 p2.setString(2, id);
		            	 p2.addBatch();
		             }
	            }
	            p2.executeBatch();
	           } catch (Exception e) {
	            	e.printStackTrace();
	           }
	           
	          finally{
	        	  if(con!=null)
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	          }
	    }
}