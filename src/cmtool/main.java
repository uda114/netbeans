package cmtool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import models.Control;
import models.Coupling;
import models.Inheritance;
import models.Methods;
import models.TableModel;
import models.Variables;
import models.size;
import org.apache.commons.io.FileUtils;

public class main extends javax.swing.JFrame {
    
    public main() {
        initComponents();
        jPanel3.setVisible(false);
        jPanel5.setVisible(false);
        pnlDisable();
    }
    
    private void pnlDisable(){
        controlStructurePnl.setVisible(false);
        inheritancePnl.setVisible(false);
        methodPnl.setVisible(false);
        variablePnl.setVisible(false);
        sizePnl.setVisible(false);
        couplingPln.setVisible(false);
    }

    private void disableBtn(){
        allFac.setSelected(false);
        sizeBtn.setSelected(false);
        VariableBtn.setSelected(false);
        MethodsBtn.setSelected(false);
        InheritanceBtn.setSelected(false);
        CouplingBtn.setSelected(false);
        ControlStructureBtn.setSelected(false);
    }
    
    public ArrayList<size> sizeCalculate(String code) {
		
		int count = 0;
		String code_variable="";
		ArrayList<size> sizeList = new ArrayList<size>();
		
		String[] lines = code.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println("line " + count++ + " : " + line);
                    String[] findString  = line.split("\"");
                    int keyword=0;
                        int Numerical=0;
                        int stringCount = 0;
                        int identifier = 0;
                        int operator=0;

                    for (int i=1;i<=findString.length;i++) {

            	if(i%2==1) {
            		
            		String test =findString[i-1];
            		
            		String regex = "abstract_|boolean_|break_|byte_|case_|catch_|char_|class_|continue_|default_|do_|double_|else_|enum_|extends_|final_|finally_|float_|for_|if_|implements_|instanceof_|interface_|long_|new_|null_|package_|private_|protected_|public_|return_|short_|String_|static_|strictfp_|super_|switch_|synchronized_|this_|throw_|throws_|transient_|try_|void_|volatile_|while_"; 
            		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            		Matcher matcher = pattern.matcher(test.replaceAll("\\s+","_"));
            		while (matcher.find())
            		{
            			keyword++;
            		}
            		
            		String str = test.replaceAll("\\d+", "_X@X_");
            		
            		String regex1 = "_X@X_";
            		Pattern ptrn1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
            		Matcher mtrn1 = ptrn1.matcher(str);
            		while (mtrn1.find())
            		{
            			Numerical++;
            		}
            		
            		String regex2 = "boolean|byte|char|double|float|int|long|short|String|Dimension"; 
            		Pattern ptrn2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
            		Matcher mtrn2 = ptrn2.matcher(test);
            		while (mtrn2.find())
            		{
            			int first=0;
            			String regex3 = ";"; 
                		Pattern ptrn3 = Pattern.compile(regex3, Pattern.CASE_INSENSITIVE);
                		Matcher mtrn3 = ptrn3.matcher(test);
                		while (mtrn3.find())
                		{
                			if(first==0) {
                    			String line_var= test.substring(mtrn2.end(),mtrn3.start());
                    			String[] var_arr  = line_var.split("=\\,\\;");
                    			for(int k = 0; k < var_arr.length; k++) {
                    				String[] var_arr1  = line_var.split(",");
                    				for(int m = 0; m < var_arr1.length; m++) {
                    					String test_var =var_arr1[m];
                    					String[] test_arr  = test_var.split("=");
                    					test_arr[0]=test_arr[0].replaceAll("\\s+","");
                    					if(code_variable=="") {
                    						
                    						boolean suc=true;
                    						
                    						String str4 = (test_arr[0].replaceAll("\\(", "_X@X_")).replaceAll("\\)", "_X@X_");
                    						
                    						String regex4 = "_X@X_"; 
                    	            		Pattern ptrn4 = Pattern.compile(regex4, Pattern.CASE_INSENSITIVE);
                    	            		Matcher mtrn4 = ptrn4.matcher(str4);
                    	            		while (mtrn4.find())
                    	            		{
                    	            			suc=false;
                    	            		}
                    	            		
                    	            		if(suc) {
                    	            			code_variable =test_arr[0]+"=|"+test_arr[0]+"<|"+test_arr[0]+".XX-X.|="+test_arr[0]+"|<"+test_arr[0]+"|.X-X."+test_arr[0]+"|"+test_arr[0]+".X-X.|-"+test_arr[0]+"|"+test_arr[0]+"-|.X-X-X."+test_arr[0]+"|"+test_arr[0]+".X-X-X.|/"+test_arr[0]+"|"+test_arr[0]+"/|%"+test_arr[0]+"|"+test_arr[0]+"%";
                    	            		}
                    					}else {
                							boolean suc=true;
                    						
                    						String str4 = (test_arr[0].replaceAll("\\(", "_X@X_")).replaceAll("\\)", "_X@X_");
                    						
                    						String regex4 = "_X@X_"; 
                    	            		Pattern ptrn4 = Pattern.compile(regex4, Pattern.CASE_INSENSITIVE);
                    	            		Matcher mtrn4 = ptrn4.matcher(str4);
                    	            		while (mtrn4.find())
                    	            		{
                    	            			suc=false;
                    	            		}
                    	            		
                    	            		if(suc) {
                    	            			code_variable = code_variable+"|"+test_arr[0]+"=|"+test_arr[0]+"<|"+test_arr[0]+".XX-X.|="+test_arr[0]+"|<"+test_arr[0]+"|.X-X."+test_arr[0]+"|"+test_arr[0]+".X-X.|-"+test_arr[0]+"|"+test_arr[0]+"-|.X-X-X."+test_arr[0]+"|"+test_arr[0]+".X-X-X.|/"+test_arr[0]+"|"+test_arr[0]+"/|%"+test_arr[0]+"|"+test_arr[0]+"%";
                    	            		}
                        					
                    					}
                    				}
                                }
                			}
                			first++;
                		}
            		}
            		
            		if(code_variable!="") {
            			String str1= test.replaceAll("\\s+","");
            			String str2= str1.replaceAll("\\+",".X-X.");
            			String str3= str2.replaceAll("\\*",".X-X-X.");
            			String str4= str3.replaceAll("\\)",".XX-X.");
            			String regex5 = code_variable; 
	            		Pattern ptrn5 = Pattern.compile(regex5, Pattern.CASE_INSENSITIVE);
	            		Matcher matcher5 = ptrn5.matcher(str4);
	            		while (matcher5.find())
	            		{
	            			identifier++;
	            		}
            		}
            		
            		String str1= test.replaceAll("\\s+","_");
            		String regex6 = "class_|interface_|void_"; 
            		Pattern ptrn6 = Pattern.compile(regex6, Pattern.CASE_INSENSITIVE);
            		Matcher matcher6 = ptrn6.matcher(str1);
            		while (matcher6.find())
            		{
            			identifier++;
            			
            			int x=0,y=0;
            			
                		String regex7 = ","; 
                		Pattern ptrn7 = Pattern.compile(regex7, Pattern.CASE_INSENSITIVE);
                		Matcher matcher7 = ptrn7.matcher(test);
                		while (matcher7.find())
                		{
                			x++;
                		}
                		
                		String regex8 = ","; 
                		Pattern ptrn8 = Pattern.compile(regex8, Pattern.CASE_INSENSITIVE);
                		Matcher matcher8 = ptrn8.matcher(test);
                		while (matcher8.find())
                		{
                			y++;
                		}
            			
                		if(x>0) {
                			identifier=identifier+x+1;
                		}
                		
                		if(y>0) {
                			identifier=identifier+y+1;
                		}
                		
            		}
            		
            		boolean imp=true;
            		
            		String regex7 = "import_"; 
            		Pattern ptrn7 = Pattern.compile(regex7, Pattern.CASE_INSENSITIVE);
            		Matcher matcher7 = ptrn7.matcher(str1);
            		while (matcher7.find())
            		{
            			imp=false;
            		}
            		
            		if(imp) {
	            		String test1= test.replaceAll("\\*;","");
	            		String test2= test1.replaceAll("\\.",".@-1@1.");
	            		String strr1= test2.replaceAll("\\|=",".@-1@1.");
	            		String str2= strr1.replaceAll("\\|","@");
	            		String str3= str2.replaceAll("\\!=",".@-1@1.");
	            		String str4= str3.replaceAll("\\>=",".@-1@1.");
	            		String str5= str4.replaceAll("\\<=",".@-1@1.");
	            		String str6= str5.replaceAll("\\==",".@-1@1.");
	            		String str7= str6.replaceAll("\\++",".@-1@1.");
	            		String str8= str7.replaceAll("\\--",".@-1@1.");
	            		String str9= str8.replaceAll("\\<<<",".@-1@1.");
	            		String str10= str9.replaceAll("\\>>>",".@-1@1.");
	            		String str11= str10.replaceAll("\\>>",".@-1@1.");
	            		String str12= str11.replaceAll("\\<<",".@-1@1.");
	            		String str13= str12.replaceAll("\\~",".@-1@1.");
	            		String str14= str13.replaceAll("\\^",".@-1@1.");
	            		String str15= str14.replaceAll("\\->",".@-1@1.");
	            		String str16= str15.replaceAll("\\::",".@-1@1.");
	            		String str17= str16.replaceAll("\\+=",".@-1@1.");
	            		String str18= str17.replaceAll("\\-=",".@-1@1.");
	            		String str19= str18.replaceAll("\\*=",".@-1@1.");
	            		String str20= str19.replaceAll("\\/=",".@-1@1.");
	            		String str21= str20.replaceAll("\\>>>=",".@-1@1.");
	            		String str22= str21.replaceAll("\\&=",".@-1@1.");
	            		String str23= str22.replaceAll("\\%=",".@-1@1.");
	            		String str24= str23.replaceAll("\\<<=",".@-1@1.");
	            		String str25= str24.replaceAll("\\>>=",".@-1@1.");
	            		String str26= str25.replaceAll("\\^=",".@-1@1.");
	            		String str27= str26.replaceAll("\\+",".@-1@1.");
	            		String str28= str27.replaceAll("\\-",".@-1@1.");
	            		String str29= str28.replaceAll("\\*",".@-1@1.");
	            		String str30= str29.replaceAll("\\/",".@-1@1.");
	            		String str31= str30.replaceAll("\\%",".@-1@1.");
	            		String str32= str31.replaceAll("\\=",".@-1@1.");
	            		String str33= str32.replaceAll("\\,",".@-1@1.");
	            		String regex8 = "&&|@@|.@-1@1.";
	            		Pattern ptrn8 = Pattern.compile(regex8, Pattern.CASE_INSENSITIVE);
	            		Matcher matcher8 = ptrn8.matcher(str33);
	            		while (matcher8.find())
	            		{
	            			operator++;
	            		}
            		}
            		
            	}else {
            		String string = findString[i-1];
            		for(int k = 0; k < string.length(); k++) {    
                        if(string.charAt(k) != ' ')    
                        	stringCount++;    
                    }
            		stringCount = stringCount + string.replaceAll("[^ ]", "").length();
            	}
            }
            
            size list = new size();

            list.setIdentifier(identifier);
            list.setKeyword(keyword);
            list.setLine(line);
            list.setlNum(count);
            list.setNumerical(Numerical);
            list.setOperator(operator);
            list.setString_Count(stringCount);

            sizeList.add(list);

            System.out.println(" ind "+identifier+" key "+keyword+" n "+Numerical+" op "+operator);

        }

        return sizeList;

    }
    
    public ArrayList<Variables> variableCalculate(String code) {
		
		int count = 0;
		int method = 0;
		ArrayList<Variables> variablesList = new ArrayList<Variables>();
		
		String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            System.out.println("line " + count++ + " : " + line);
            String[] findString  = line.split("\"");
            int Global =0;
            int local=0;
            int composite=0;
            
            for (int i=1;i<=findString.length;i++) {
        		
            	if(i%2==1) {
            		
            		String test =findString[i-1];
            		
        			String str= test.replaceAll("\\{","@X@");
        			
        			String mainString= test.replaceAll("\\s+","_");
            		
            		String regex = "@X@"; 
            		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            		Matcher matcher = pattern.matcher(str);
            		while (matcher.find())
            		{
            			method++;
            		}
            		
            		if(method==1) {
        				
        				String regex1 = "_boolean_|_byte_|_char_|_double_|_float_|_int_|_long_|_short_|_String_"; 
                		Pattern pttrn1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
                		Matcher mtrn1 = pttrn1.matcher(mainString);
                		while (mtrn1.find())
                		{
                			String regex2 = ";"; 
                    		Pattern pttrn2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
                    		Matcher mtrn2 = pttrn2.matcher(test);
                    		while (mtrn2.find())
                    		{
                    			String line_var= test.substring(mtrn1.end(),mtrn2.start());
                    			String[] var_arr  = line_var.split("=\\,\\;");
                    			for(int k = 0; k < var_arr.length; k++) {
                    				String[] var_arr1  = line_var.split(",");
                    				for(int m = 0; m < var_arr1.length; m++) {
                						Global++;
                    				}
                                }
                    		}
                    		
                    		String str1 = test.replaceAll("\\d+", "_X_");
                    		String str2 = str1.replaceAll("\\[", "@");
                    		String str3 = str2.replaceAll("\\]", ".");
                    		String regex3 = "@_X_.|@."; 
                    		Pattern pttrn3 = Pattern.compile(regex3, Pattern.CASE_INSENSITIVE);
                    		Matcher mtrn3 = pttrn3.matcher(str3);
                    		while (mtrn3.find())
                    		{
                    			String regex4 = ";"; 
                        		Pattern pttrn4 = Pattern.compile(regex4, Pattern.CASE_INSENSITIVE);
                        		Matcher mtrn4 = pttrn2.matcher(test);
                        		while (mtrn4.find())
                        		{
                        			String line_var= test.substring(mtrn1.end(),mtrn4.start());
                        			String[] var_arr  = line_var.split("=\\,\\;");
                        			for(int k = 0; k < var_arr.length; k++) {
                        				String[] var_arr1  = line_var.split(",");
                        				for(int m = 0; m < var_arr1.length; m++) {
                    						composite++;
                        				}
                                    }
                        		}
                    		}
                    		
                		}
                		String regex4 = "_Dimension_"; 
                		Pattern pttrn4 = Pattern.compile(regex4, Pattern.CASE_INSENSITIVE);
                		Matcher mtrn4 = pttrn4.matcher(mainString);
                		while (mtrn4.find())
                		{
                			composite++;
                		}
        				
        			}
            		
            		
            		if(method>1) {
            			String regex1 = "if|while|do|for"; 
                		Pattern pttrn1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
                		Matcher mtrn1 = pttrn1.matcher(test);
                		
            			if(!mtrn1.find()) {
            				String regex3 = "_boolean_|_byte_|_char_|_double_|_float_|_int_|_long_|_short_|_String_"; 
                    		Pattern pttrn3 = Pattern.compile(regex3, Pattern.CASE_INSENSITIVE);
                    		Matcher mtrn3 = pttrn3.matcher(mainString);
                    		while (mtrn3.find())
                    		{
                    			String regex2 = ";"; 
                        		Pattern pttrn2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
                        		Matcher mtrn2 = pttrn2.matcher(test);
                        		while (mtrn2.find())
                        		{
                        			String line_var= test.substring(mtrn3.end(),mtrn2.start());
                        			String[] var_arr  = line_var.split("=\\,\\;");
                        			for(int k = 0; k < var_arr.length; k++) {
                        				String[] var_arr1  = line_var.split(",");
                        				for(int m = 0; m < var_arr1.length; m++) {
                    						local++;
                    						System.out.println(var_arr1[m]);
                        				}
                                    }
                        		}
                        		

                        		String str1 = test.replaceAll("\\d+", "_X_");
                        		String str2 = str1.replaceAll("\\[", "@");
                        		String str3 = str2.replaceAll("\\]", ".");
                        		String regex4 = "@_X_.|@."; 
                        		Pattern pttrn4 = Pattern.compile(regex4, Pattern.CASE_INSENSITIVE);
                        		Matcher mtrn4 = pttrn4.matcher(str3);
                        		while (mtrn4.find())
                        		{
                        			String regex5 = ";"; 
                            		Pattern pttrn5 = Pattern.compile(regex5, Pattern.CASE_INSENSITIVE);
                            		Matcher matcher5 = pttrn2.matcher(test);
                            		while (matcher5.find())
                            		{
                            			String line_var= test.substring(mtrn3.end(),matcher5.start());
                            			String[] var_arr  = line_var.split("=\\,\\;");
                            			for(int k = 0; k < var_arr.length; k++) {
                            				String[] var_arr1  = line_var.split(",");
                            				for(int m = 0; m < var_arr1.length; m++) {
                    							composite++;
                            				}
                                        }
                            		}
                        		}
                    		}
                    		
                    		String regex4 = "_Dimension_"; 
                    		Pattern pttrn4 = Pattern.compile(regex4, Pattern.CASE_INSENSITIVE);
                    		Matcher mtrn4 = pttrn4.matcher(mainString);
                    		while (mtrn4.find())
                    		{
                    			composite++;
                    		}
            			}
            		}
            	}
            	System.out.println(" g "+Global+" l "+local+" c "+composite);
            }
            Variables list = new Variables();
            
            list.setLine(line);
            list.setlNum(count);
            list.setComposite(composite);
            list.setGlobal(Global);
            list.setLocal(local);

            variablesList.add(list);
        }
            return variablesList;

    }
    
    public ArrayList<Methods> methodCalculate(String code) {
		
        int count = 0;
        ArrayList<Methods> list = new ArrayList<Methods>();
		
        String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            count++;
            String[] code_line_string  = line.split("\"");
            int primitive_Methods=0;
            int composite_Methods=0;
            int primitive_Parameters=0;
            int composite_Parameters=0;
            
            for (int i=1;i<=code_line_string.length;i++) {
        		
            	if(i%2==1) {
            		
                    String test =code_line_string[i-1];

                    String str= test.replaceAll("\\s+","_");

                    String reg_ptr = "public_|protected_|private_"; 
                    Pattern pattern = Pattern.compile(reg_ptr, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find())
                    {
                            String smpl_str1= test.replaceAll("\\s+","");
                            String smpl_str2= smpl_str1.replaceAll("\\)","@");
                            String smpl_str3= smpl_str2.replaceAll("\\{","._@1");

                            String reg_models1 = "@._@1"; 
                            Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                            Matcher mtr1 = pttrn1.matcher(smpl_str3);
                            while (mtr1.find())
                            {
                                boolean type=false;

                                String reg_models2 = "_void_"; 
                                Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                                Matcher mtr2 = pttrn2.matcher(str);
                                while (mtr2.find())
                                {
                                        type=true;
                                }
                                if(!type) {

                                        String smpl_str4= test.replaceAll("\\(","@@");
                                        String[] sample  = smpl_str4.split("@@");

                                        String smpl_str5= sample[0].replaceAll("\\s+","_");
                                        String reg_models3 = "_boolean_|_byte_|_char_|_double_|_float_|_int_|_long_|_short_|_String_"; 
                                        Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
                                        Matcher mtr3 = pttrn3.matcher(smpl_str5);
                                        while (mtr3.find())
                                        {
                                                primitive_Methods++;
                                        }

                                        if(primitive_Methods==0) {
                                                composite_Methods++;
                                        }

                                        int data_types=0;

                                        String smpl_str6= sample[1].replaceAll("\\s+","_");
                                        String reg_models4 = "boolean_|byte_|char_|double_|float_|int_|long_|short_|String_"; 
                                        Pattern pttrn4 = Pattern.compile(reg_models4, Pattern.CASE_INSENSITIVE);
                                        Matcher mtr4 = pttrn4.matcher(smpl_str6);
                                        while (mtr4.find())
                                        {
                                                data_types++;
                                        }

                                        int parameters=1;

                                        String reg_models5 = ","; 
                                        Pattern ptrn5 = Pattern.compile(reg_models5, Pattern.CASE_INSENSITIVE);
                                        Matcher mtr5 = ptrn5.matcher(sample[1]);
                                        while (mtr5.find())
                                        {
                                                parameters++;
                                        }

                                        int no_para=0;

                                        String smpl_str7= sample[1].replaceAll("\\s+","");
                                        String smpl_str8= smpl_str7.replaceAll("\\)","@");
                                        String reg_models6 = "@@"; 
                                        Pattern ptrn6 = Pattern.compile(reg_models6, Pattern.CASE_INSENSITIVE);
                                        Matcher mtr6 = ptrn6.matcher("@"+smpl_str8);
                                        while (mtr6.find())
                                        {
                                                no_para++;
                                        }

                                        int comp=0;

                                        String smpl_str9= sample[1].replaceAll("\\[","@@");
                                        String reg_ptr9 = "@@"; 
                                        Pattern pattern9 = Pattern.compile(reg_ptr9, Pattern.CASE_INSENSITIVE);
                                        Matcher matcher9 = ptrn6.matcher(smpl_str9);
                                        while (matcher9.find())
                                        {
                                                comp++;
                                        }

                                        if(no_para!=0) {
                                                primitive_Parameters=0;
                                                composite_Parameters=0;
                                        }else if((data_types==parameters)&&comp==0){
                                                primitive_Parameters=data_types;
                                                composite_Parameters=0;
                                        }else if((data_types!=parameters)&&comp==0){
                                                primitive_Parameters=data_types;
                                                composite_Parameters=parameters-data_types;
                                        }else if((comp==parameters)&&data_types!=0){
                                                primitive_Parameters=0;
                                                composite_Parameters=parameters;
                                        }else{
                                                primitive_Parameters=data_types;
                                                composite_Parameters=parameters;
                                        }

                                }else {
                                	String smpl_str4= test.replaceAll("\\(","@@");
                                    String[] sample  = smpl_str4.split("@@");

                                    String smpl_str5= sample[1].replaceAll("\\s+","_");
                                    String reg_models3 = "Graphics_|MouseEvent_"; 
                                    Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
                                    Matcher mtr3 = pttrn3.matcher(smpl_str5);
                                    while (mtr3.find())
                                    {
                                    	composite_Parameters++;
                                    }
                                }
                            }
                    }
            	}
            }
            Methods method = new Methods();
            
            method.setLine(line);
            method.setlNum(count);
            method.setCompositeMethod(composite_Methods);
            method.setCompositeParameters(composite_Parameters);
            method.setPrimitiveMethod(primitive_Methods);
            method.setPrimitiveParameters(primitive_Parameters);

            list.add(method);
        }
        return list;
    }
    
    public ArrayList<Control> controlCalculate(String code, int ifz, int loopz, int swthz, int csez) {
		
        int count = 0;
        int if_weight = ifz;
        int loop = loopz;
        int weight_Switch = swthz;
        int weight_Case = csez;
        String control="";
        boolean checkCon=false;
        ArrayList<Control> list = new ArrayList<Control>();

        String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            count++;
            String[] code_line_string  = line.split("\"");
            int no_Of_Control=0;
            int loops_Count=0;
            int condition_count=0;
            int count_Switch=0;
            int line_Weight=0;
            int case_Count=0;
            int cspps=0;
            
            for (int i=1;i<=code_line_string.length;i++) {
        		
            	if(i%2==1) {
            		
            		String test =code_line_string[i-1];
            		
        			String smpl_str= (test.replaceAll("\\s+","_")).replaceAll("\\(","@");
        			String reg_ptr = "do@|if@|while@|case_|switch@|do_@|if_@|while_@|switch_@|for_@|for@"; 
            		Pattern pattern = Pattern.compile(reg_ptr, Pattern.CASE_INSENSITIVE);
            		Matcher matcher = pattern.matcher(smpl_str);
            		while (matcher.find())
            		{
            			no_Of_Control++;
            			
            			String smpl_str2= test.replaceAll("\\s+","");
            			String smpl_str3= smpl_str2.replaceAll("\\(","@");
            			String reg_models1 = "if@"; 
                		Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                		Matcher mtr1 = pttrn1.matcher(smpl_str3);
                		while (mtr1.find())
                		{
                			control = control+"if#";
                			condition_count++;
                		}
                		
                		String reg_models2 = "for@"; 
                		Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                		Matcher mtr2 = pttrn2.matcher(smpl_str3);
                		while (mtr2.find())
                		{
                			control = control+"for#";
                			loops_Count++;
                		}

            			String smpl_str4= smpl_str2.replaceAll("\\{","@");
                		String reg_models3 = "do@"; 
                		Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
                		Matcher mtr3 = pttrn3.matcher(smpl_str4);
                		while (mtr3.find())
                		{
                			control = control+"do#";
                			loops_Count++;
                		}
                		
                		String reg_models4 = "while@"; 
                		Pattern pttrn4 = Pattern.compile(reg_models4, Pattern.CASE_INSENSITIVE);
                		Matcher mtr4 = pttrn4.matcher(smpl_str3);
                		while (mtr4.find())
                		{
                			String smpl_str5= smpl_str2.replaceAll("\\)","@");
                			String smpl_str6= smpl_str5.replaceAll("\\{","-.X");
                			
                			String reg_models5 = "@-.X"; 
                    		Pattern ptrn5 = Pattern.compile(reg_models5, Pattern.CASE_INSENSITIVE);
                    		Matcher mtr5 = ptrn5.matcher(smpl_str6);
                    		while (mtr5.find())
                    		{
                    			control = control+"while#";
                    			loops_Count++;
                    		}
                		}
                		
            			String reg_models6 = "switch@"; 
                		Pattern ptrn6 = Pattern.compile(reg_models6, Pattern.CASE_INSENSITIVE);
                		Matcher mtr6 = ptrn6.matcher(smpl_str3);
                		while (mtr6.find())
                		{
                			control = control+"switch#";
                			count_Switch++;
                		}
                		
                		String reg_models7 = "@@"; 
                		Pattern ptrn7 = Pattern.compile(reg_models7, Pattern.CASE_INSENSITIVE);
                		Matcher mtr7 = ptrn7.matcher(test.replaceAll("\\&&","@@"));
                		while (mtr7.find())
                		{
                			no_Of_Control++;
                		}
                		checkCon=true;
            		}
            		
            		if(checkCon) {
            			String smpl_str1= (test.replaceAll("\\s+","_")).replaceAll("\\}","@@");
            			String reg_models1 = "@@"; 
                		Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                		Matcher mtrn1 = pttrn1.matcher(smpl_str1);
                		while (mtrn1.find())
                		{
                			if(control!="") {
                				String[] lop  = control.split("#");
                				control="";
                				for(int j=0;j<lop.length-1;j++) {
                					control=control+lop[j]+"#";
                				}
                			}
                		}
            		}
            	}
            }
            
            case_Count=no_Of_Control-count_Switch-loops_Count-condition_count;
            
            if(no_Of_Control>0) {
            	line_Weight=(count_Switch*weight_Switch)+case_Count+(loops_Count*loop)+(condition_count*if_weight);
            	
                String[] lop  = control.split("#");
                if(lop.length>1) {
                	int current=lop.length;
                	
                	for(int j=0;j<current-1;j++) {
                		
                		if(lop[j].equals("if")) {
                			cspps=cspps+if_weight;
                		}else if(lop[j].equals("for")||lop[j].equals("do")||lop[j].equals("while")) {
                			cspps=cspps+loop;
                		}else if(lop[j].equals("switch")) {
                			cspps=cspps+weight_Switch;
                		}
                		
                	}
                	
                }
            	
            }
            
            Control controls = new Control();
            
            controls.setLine(line);
            controls.setlNum(count);
            controls.setCspps(cspps);
            controls.setLineWeight(line_Weight);
            controls.setNoOfControl(no_Of_Control);
            
            list.add(controls);
            
        }
		
        return list;
		
    }
    
    public ArrayList<Coupling> couplingCalculate(String code) {
		
        int count = 0;
        boolean inside_Method=false;
        int inside_Method_count=0;
        int line_Count=0;
        String[] m_Store=finding_methods(code).split("@");
        String m_Name=null;
        ArrayList<Coupling> list = new ArrayList<Coupling>();
        int insideClass =0;

        String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            count++;
            String[] code_line_string  = line.split("\"");
            int regular_Global=0;
            int recursive_Global=0;
            int recursive=0;
            int regular_Regular=0;
            int regular_Recursive=0;
            int recursive_Regular=0;
            int recursive_Recursive=0;
    		
            for (int i=1;i<=code_line_string.length;i++) {
        		
            	if(i%2==1) {
            		
            		String test =code_line_string[i-1];
            		
        			String str= test.replaceAll("\\s+","_");
        			
        			String reg_ptr = "public_|protected_|private_";
            		Pattern pattern = Pattern.compile(reg_ptr, Pattern.CASE_INSENSITIVE);
            		Matcher matcher = pattern.matcher(str);
            		while (matcher.find())
            		{
            			String smpl_str1= test.replaceAll("\\s+","");
            			String smpl_str2= smpl_str1.replaceAll("\\)","@");
            			String smpl_str3= smpl_str2.replaceAll("\\{","-.X");
            			
            			String reg_models1 = "@-.X"; 
                		Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                		Matcher mtr1 = pttrn1.matcher(smpl_str3);
                		while (mtr1.find())
                		{
                			String smpl_str4= test.replaceAll("\\(","@@");
                			String[] sample  = smpl_str4.split("@@");
                			
                			String smpl_str5= sample[0].replaceAll("\\s+","_");
                			String reg_models3 = "_boolean_|_byte_|_char_|_double_|_float_|_int_|_long_|_short_|_String_|_void_"; 
                    		Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
                    		Matcher mtr3 = pttrn3.matcher(smpl_str5);
                    		while (mtr3.find())
                    		{
                    			String[] sample1  = (smpl_str5.substring(mtr3.end(),smpl_str5.length())).split("_");
                    			m_Name = sample1[0];
                    			inside_Method=true;
                    		}
                    		
                		}

            		}
            		
            		if(inside_Method) {
            			String reg_models2 = "get|set|add"; 
                		Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                		Matcher mtr2 = pttrn2.matcher(test);
                		while (mtr2.find())
                		{
                			boolean ch=false;
                			String test2= test.replaceAll("\\(","-.X");
                    		String reg_models3 = "-.X"; 
                    		Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
                    		Matcher mtr3 = pttrn2.matcher(test2);
                    		while (mtr3.find())
                    		{
                    			ch=true;
                    		}
                    		
                    		if(ch) {
                    			regular_Regular++;
                    		}
                    		
                		}
            		}
            		
            		if(inside_Method) {
            			line_Count++;

                		String smpl_str1= test.replaceAll("\\{","@@");
            			
            			String reg_models1 = "@@"; 
                		Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                		Matcher mtr1 = pttrn1.matcher(smpl_str1);
                		while (mtr1.find())
                		{
                			inside_Method_count++;
                		}
                		
                		String smpl_str2= test.replaceAll("\\}","@@");
            			
            			String reg_models2 = "@@"; 
                		Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                		Matcher mtr2 = pttrn2.matcher(smpl_str2);
                		while (mtr2.find())
                		{
                			inside_Method_count--;
                		}
                		
                		if(inside_Method_count==0) {
                			inside_Method=false;
                			line_Count=0;
                			m_Name=null;
                			insideClass=0;
                		}else {
                			for(int k = 0; k < m_Store.length; k++) {
                				if(k%2==0) {
                					boolean check=false;
                        			
                					String smpl_str3= str.replaceAll("\\(","@");
                					String smpl_str4= smpl_str3.replaceAll("\\+","@@");
                					String smpl_str5= smpl_str4.replaceAll("\\-","@@");
                					String smpl_str6= smpl_str5.replaceAll("\\*","@@");
                					String smpl_str7= smpl_str6.replaceAll("\\/","@@");
                					
                        			String reg_models3 = "_"+m_Store[k]+"_|_"+m_Store[k]+"@|@@"+m_Store[k];
                            		Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
                            		Matcher mtr3 = pttrn3.matcher(smpl_str7);
                            		while (mtr3.find())
                            		{
                            			check=true;
                            		}
                            		
                            		if(check) {
                            			if(m_Store[k+1]=="Global") {
                            				
                            				for(int m = 0; m < m_Store.length; m++) {
                            					if(m%2==0) {
                            						if(m_Name==m_Store[m]) {
                            							if(m_Store[m+1].equals("regular")) {
                            								regular_Global++;
                            							}else {
                            								recursive_Global++;
                            							}
                            						}
                            					}
                            				}
                            				
                            			}else {
                            				if(line_Count>1) {
                                				if(m_Name.equals(m_Store[k])) {
                                					recursive++;
                                				}else {
                                					
                                					for(int m = 0; m < m_Store.length; m++) {
                                    					if(m%2==0) {
                                    						if(m_Name.equals(m_Store[m])) {
                                    							if(m_Store[m+1].equals("regular")) {
                                    								if(m_Store[k+1].equals("regular")) {
                                    									regular_Regular++;
                                    								}else {
                                    									regular_Recursive++;
                                    								}
                                    							}else {
                                    								if(m_Store[k+1].equals("regular")) {
                                    									recursive_Regular++;
                                    								}else {
                                    									recursive_Recursive++;
                                    								}
                                    							}
                                    						}
                                    					}
                                    				}
                                					
                                				}
                            				}
                            			}
                            		}
                				}
                			}
                		}
            		}
            	}
            }
            
            Coupling coupling = new Coupling();
            
            coupling.setLine(line);
            coupling.setlNum(count);
            coupling.setRecursive(recursive);
            coupling.setRecursiveGlobal(recursive_Global);
            coupling.setRecursiveRecursive(recursive_Recursive);
            coupling.setRecursiveRegular(recursive_Regular);
            coupling.setRegularRecursive(regular_Recursive);
            coupling.setRegularRegular(regular_Regular);
            coupling.setRegularGlobal(regular_Global);
            
            list.add(coupling);
            
        }
		
        return list;
		
    }
	
    public String finding_methods(String code) {
		
        int count = 0;
        boolean inside_Method=false;
        int inside_Method_count=0;
        int line_Count=0;
        boolean regular=true;
        String m_Store="";
        String m_Name=null;

        String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            count++;
            String[] code_line_string  = line.split("\"");
            
            for (int i=1;i<=code_line_string.length;i++) {
        		
            	if(i%2==1) {
            		
            		String test =code_line_string[i-1];
            		
        			String str= test.replaceAll("\\s+","_");
        			
        			String reg_ptr = "public_|protected_|private_"; 
            		Pattern pattern = Pattern.compile(reg_ptr, Pattern.CASE_INSENSITIVE);
            		Matcher matcher = pattern.matcher(str);
            		while (matcher.find())
            		{
            			String smpl_str1= test.replaceAll("\\s+","");
            			String smpl_str2= smpl_str1.replaceAll("\\)","@");
            			String smpl_str3= smpl_str2.replaceAll("\\{","-.X");
            			
            			String reg_models1 = "@-.X"; 
                		Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                		Matcher mtr1 = pttrn1.matcher(smpl_str3);
                		while (mtr1.find())
                		{
                			String smpl_str4= test.replaceAll("\\(","@@");
                			String[] sample  = smpl_str4.split("@@");
                			
                			String smpl_str5= sample[0].replaceAll("\\s+","_");
                			String reg_models3 = "_boolean_|_byte_|_char_|_double_|_float_|_int_|_long_|_short_|_String_|_void_"; 
                    		Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
                    		Matcher mtr3 = pttrn3.matcher(smpl_str5);
                    		while (mtr3.find())
                    		{
                    			String[] sample1  = (smpl_str5.substring(mtr3.end(),smpl_str5.length())).split("_");
                    			m_Name = sample1[0];
                    			inside_Method=true;
                    		}
                    		
                		}
            		}
            		
            		if(inside_Method) {
            			line_Count++;

                		String smpl_str1= test.replaceAll("\\{","@@");
            			
            			String reg_models1 = "@@"; 
                		Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                		Matcher mtr1 = pttrn1.matcher(smpl_str1);
                		while (mtr1.find())
                		{
                			inside_Method_count++;
                		}
                		
                		String smpl_str2= test.replaceAll("\\}","@@");
            			
            			String reg_models2 = "@@"; 
                		Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                		Matcher mtr2 = pttrn2.matcher(smpl_str2);
                		while (mtr2.find())
                		{
                			inside_Method_count--;
                		}
                		
                		if(line_Count>1) {
                			
	                		String smpl_str3= test.replaceAll("\\s+","_").replaceAll("\\(","@@");
	            			
	            			String reg_models3 = "_"+m_Name+"@@|_"+m_Name+"_"; 
	                		Pattern pttrn3 = Pattern.compile(reg_models3, Pattern.CASE_INSENSITIVE);
	                		Matcher mtr3 = pttrn3.matcher(smpl_str3);
	                		while (mtr3.find())
	                		{
	                			regular=false;
	                		}
                		}
                		
                		
                		if(inside_Method_count==0) {
                			inside_Method=false;
                			line_Count=0;
                			if(regular) {
                				if(m_Store=="") {
                					m_Store=m_Name+"@regular";
                				}else {
                					m_Store=m_Store+"@"+m_Name+"@regular";
                				}
                			}else {
                				if(m_Store=="") {
                					m_Store=m_Name+"@recursive";
                				}else {
                					m_Store=m_Store+"@"+m_Name+"@recursive";
                				}
                			}
                			m_Name=null;
                			regular=true;
                		}
                		
            		}else {
            			
            			String reg_models1 = "boolean|byte|char|double|float|int|long|short|String"; 
                		Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                		Matcher mtr1 = pttrn1.matcher(test);
                		while (mtr1.find())
                		{
                			String reg_models2 = ";"; 
                    		Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                    		Matcher mtr2 = pttrn2.matcher(test);
                    		while (mtr2.find())
                    		{
                    			String line_var= test.substring(mtr1.end(),mtr2.start());
                    			String[] var_arr  = line_var.split("=\\,\\;");
                    			for(int k = 0; k < var_arr.length; k++) {
                    				String[] var_arr1  = line_var.split(",");
                    				for(int m = 0; m < var_arr1.length; m++) {
                    					if(m==0) {
                    						if(m_Store=="") {
                    							m_Store=var_arr1[0]+"@Global";
                    						}else {
                    							m_Store=m_Store+"@"+var_arr1[0]+"@Global";
                    						}
                    					}
                    				}
                                }
                    		}
                		}
            		}
            		
            	}
            }
        }
        
        return m_Store;

    }
    
    public ArrayList<Inheritance> inheritanceCalculate(String code) {
		
        int count = 0;
        int classCount=0;
        ArrayList<Inheritance> list = new ArrayList<Inheritance>();

        String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            count++;
            String[] code_line_string  = line.split("\"");
            String className=null;
            int direct=0;
            int indirect=0;
            
            for (int i=1;i<=code_line_string.length;i++) {
        		
            	if(i%2==1) {
            		
                    String test =code_line_string[i-1];

                    String str= test.replaceAll("\\s+","_");

                    String reg_ptr = "class_"; 
                    Pattern pattern = Pattern.compile(reg_ptr, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find())
                    {
                            boolean ex=false;

                            String reg_models1 = "_extends_"; 
                            Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                            Matcher mtr1 = pttrn1.matcher(str);
                            while (mtr1.find())
                            {

                                ex=true;
                                String clsName = test.substring(matcher.end(),mtr1.start());

                                className=clsName.replaceAll("\\s+","");
                                
                                String reg_models4 = "_implements_"; 
                                Pattern pttrn4 = Pattern.compile(reg_models4, Pattern.CASE_INSENSITIVE);
                                Matcher mtr4 = pttrn4.matcher(str);
                                while (mtr4.find())
                                {
                                	boolean check=false;
                                	String testClass = test.substring(mtr1.end(),mtr4.start());
                                	
                                	String reg_models5 = "."; 
                                    Pattern ptrn5 = Pattern.compile(reg_models5, Pattern.CASE_INSENSITIVE);
                                    Matcher mtr5 = ptrn5.matcher(testClass);
                                    while (mtr5.find())
                                    {
                                    	check=true;
                                    }
                                    
                                    if(check) {
                                    	direct--;
                                    }
                                }
                                direct++;

                                String smpl_str1= str.replaceAll("\\{","@");

                                String reg_models2 = "_@|@"; 
                                Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                                Matcher mtr2 = pttrn2.matcher(smpl_str1);
                                while (mtr2.find())
                                {

                                        String extName = (test.substring(mtr1.end(),mtr2.start())).replaceAll("\\s+","");

                                        indirect=loops_Counting(code,extName,0);

                                }

                            }

                            if(ex==false) {

                                String smpl_str1= str.replaceAll("\\{","@");

                                String reg_models2 = "_@|@"; 
                                Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                                Matcher mtr2 = pttrn2.matcher(smpl_str1);
                                while (mtr2.find())
                                {
                                        String clsName = test.substring(matcher.end(),mtr2.start());

                                        className=clsName.replaceAll("\\s+","");

                                }
                            }
                    classCount++;
                    Inheritance inheritance = new Inheritance();

                    inheritance.setlNum(classCount);
                    inheritance.setClass_Name(className);
                    inheritance.setDirect(direct);
                    inheritance.setIndirect(indirect);

                    list.add(inheritance);

                    }
            		
            	}
            }
        }
		
        return list;
		
    }
	
    private int loops_Counting(String code,String ext,int indirect) {

        int count = 0;

        String[] lines = code.split("\\r?\\n");
        for (String line : lines) {
            count++;
            String[] code_line_string  = line.split("\"");

            for (int i=1;i<=code_line_string.length;i++) {

                if(i%2==1) {

                    String test =code_line_string[i-1];

                    String str= test.replaceAll("\\s+","");

                    String reg_ptr = "class"+ext; 
                    Pattern pattern = Pattern.compile(reg_ptr, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find())
                    {
                            boolean ex=false;

                            String smpl_str1= test.replaceAll("\\s+","_");
                            String reg_models1 = "_extends_"; 
                            Pattern pttrn1 = Pattern.compile(reg_models1, Pattern.CASE_INSENSITIVE);
                            Matcher mtr1 = pttrn1.matcher(smpl_str1);
                            while (mtr1.find())
                            {
                                    ex=true;

                                    String smpl_str2= smpl_str1.replaceAll("\\{","@");

                                    String reg_models2 = "_@|@"; 
                            Pattern pttrn2 = Pattern.compile(reg_models2, Pattern.CASE_INSENSITIVE);
                            Matcher mtr2 = pttrn2.matcher(smpl_str2);
                            while (mtr2.find())
                            {

                                    String extName = (test.substring(mtr1.end(),mtr2.start())).replaceAll("\\s+","");

                                    indirect++;

                                    return loops_Counting(code,extName,indirect);

                            }
                            }

                            if(ex==false) {

                                    return indirect;

                            }

                    }

                }
            }
        }
        return indirect;
    }
    
    public void displaySize(String code, int key, int idnr, int opr, int nmrc, int str){
    
        ArrayList<size> list = sizeCalculate(code);
        
        String[] colNames = {"Line no","Program statements","Nkw","Nid","Nop","Nnv","Nsl","Cs"};
        Object[][]rows= new Object[list.size()][8];
        
        for(int i=0;i<list.size();i++){
            
            rows[i][0] = list.get(i).getlNum();
            rows[i][1] = list.get(i).getLine();
            rows[i][2] = list.get(i).getKeyword();
            rows[i][3] = list.get(i).getIdentifier();
            rows[i][4] = list.get(i).getOperator();
            rows[i][5] = list.get(i).getNumerical();
            rows[i][6] = list.get(i).getString_Count();
            rows[i][7] = (list.get(i).getKeyword()*key)+(list.get(i).getIdentifier()*idnr)+(list.get(i).getOperator()*opr)+(list.get(i).getNumerical()*nmrc)+(list.get(i).getString_Count()*str);
            
            
        }
        
        TableModel tbm= new TableModel(rows,colNames);
        sizeTbl.setModel(tbm);
        sizeTbl.setRowHeight(20);
        sizeTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        sizeTbl.getColumnModel().getColumn(1).setPreferredWidth(400);
        sizeTbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(4).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(6).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(7).setPreferredWidth(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        sizeTbl.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
        sizeTbl.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
        
        ((DefaultTableCellRenderer)sizeTbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        sizeTbl.setShowGrid(true);
        sizeTbl.setGridColor(Color.black);
        sizeTbl.setSelectionBackground(Color.DARK_GRAY);
        
        JTableHeader th = sizeTbl.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.BOLD,15));
        
    }
    
    public void displayVariable(String code, int gbl, int lcl, int pmtv, int com){
    
        ArrayList<Variables> list = variableCalculate(code);
        
        String[] colNames = {"Line no","Program statements","Wvs","Npdtv","Ncdtv","Cv"};
        Object[][]rows= new Object[list.size()][6];
        
        for(int i=0;i<list.size();i++){
            
            int Wvs =list.get(i).getGlobal()*gbl+list.get(i).getLocal()*lcl;
            
            int Npdtv = list.get(i).getGlobal()+list.get(i).getLocal();
            
            int Cv = Wvs*(Npdtv+list.get(i).getComposite()*pmtv+list.get(i).getComposite()*com);
            
            rows[i][0] = list.get(i).getlNum();
            rows[i][1] = list.get(i).getLine();
            rows[i][2] = Wvs;
            rows[i][3] = Npdtv;
            rows[i][4] = list.get(i).getComposite();
            rows[i][5] = Cv;
            
            
        }
        
        TableModel tbm= new TableModel(rows,colNames);
        sizeTbl.setModel(tbm);
        sizeTbl.setRowHeight(20);
        sizeTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        sizeTbl.getColumnModel().getColumn(1).setPreferredWidth(400);
        sizeTbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(4).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        sizeTbl.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
        sizeTbl.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        
        ((DefaultTableCellRenderer)sizeTbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        sizeTbl.setShowGrid(true);
        sizeTbl.setGridColor(Color.black);
        sizeTbl.setSelectionBackground(Color.DARK_GRAY);
        
        JTableHeader th = sizeTbl.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.BOLD,15));
        
    }
    
    public void displayMethod(String code, int prmtv, int com, int ppara, int cpara){
    
        ArrayList<Methods> list = methodCalculate(code);
        
        String[] colNames = {"Line no","Program statements","Wmrt","Npdtp","Ncdtp","Cm"};
        Object[][]rows= new Object[list.size()][6];
        
        for(int i=0;i<list.size();i++){
            
            int Wmrt =list.get(i).getCompositeMethod()*com+list.get(i).getPrimitiveMethod()*prmtv;
            
            int Cm = Wmrt+(list.get(i).getPrimitiveParameters()*ppara+list.get(i).getCompositeParameters()*cpara);
            
            rows[i][0] = list.get(i).getlNum();
            rows[i][1] = list.get(i).getLine();
            rows[i][2] = Wmrt;
            rows[i][3] = list.get(i).getPrimitiveParameters();
            rows[i][4] = list.get(i).getCompositeParameters();
            rows[i][5] = Cm;
            
            
        }
        
        TableModel tbm= new TableModel(rows,colNames);
        sizeTbl.setModel(tbm);
        sizeTbl.setRowHeight(20);
        sizeTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        sizeTbl.getColumnModel().getColumn(1).setPreferredWidth(400);
        sizeTbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(4).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        sizeTbl.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
        sizeTbl.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        
        ((DefaultTableCellRenderer)sizeTbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        sizeTbl.setShowGrid(true);
        sizeTbl.setGridColor(Color.black);
        sizeTbl.setSelectionBackground(Color.DARK_GRAY);
        
        JTableHeader th = sizeTbl.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.BOLD,15));
        
    }
    
    public void displayInheritance(String code, int incr0, int incr1, int incr2, int incr3, int incr4){
    
        ArrayList<Inheritance> list = inheritanceCalculate(code);
        
        String[] colNames = {"Count","Class Name","No of direct inheritances","No of indirect inheritances","Total inheritances","Ci"};
        Object[][]rows= new Object[list.size()][6];
        
        for(int i=0;i<list.size();i++){
            
            int Ci=list.get(i).getDirect()+list.get(i).getIndirect();
            
            if(Ci==0){
                Ci=incr0;
            }else if(Ci==1){
                Ci=incr1;
            }else if(Ci==2){
                Ci=incr2;
            }else if(Ci==3){
                Ci=incr3;
            }else if(Ci>4){
                Ci=incr4;
            }
            
            rows[i][0] = list.get(i).getlNum();
            rows[i][1] = list.get(i).getClass_Name();
            rows[i][2] = list.get(i).getDirect();
            rows[i][3] = list.get(i).getIndirect();
            rows[i][4] = list.get(i).getDirect()+list.get(i).getIndirect();
            rows[i][5] = Ci;
            
            
        }
        
        TableModel tbm= new TableModel(rows,colNames);
        sizeTbl.setModel(tbm);
        sizeTbl.setRowHeight(20);
        sizeTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        sizeTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
        sizeTbl.getColumnModel().getColumn(2).setPreferredWidth(200);
        sizeTbl.getColumnModel().getColumn(3).setPreferredWidth(200);
        sizeTbl.getColumnModel().getColumn(4).setPreferredWidth(200);
        sizeTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        sizeTbl.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
        sizeTbl.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        
        ((DefaultTableCellRenderer)sizeTbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        sizeTbl.setShowGrid(true);
        sizeTbl.setGridColor(Color.black);
        sizeTbl.setSelectionBackground(Color.DARK_GRAY);
        
        JTableHeader th = sizeTbl.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.BOLD,15));
        
    }
    
    public void displayCoupling(String code, int recCup, int rrCup, int rrecCup, int recrecCup, int recrCup, int rgCup, int recgCup){
    
        ArrayList<Coupling> list = couplingCalculate(code);
        
        String[] colNames = {"Line no","Program statements","Nr","Nmcms","Nmcmd","Nmcrms","Nmcrmd","Nrmcrms","Nrmcrmd","Nrmcms","Nrmcmd","Nmrgvs","Nmrgvd","Nrmrgvs","Nrmrgvd","Ccp"};
        Object[][]rows= new Object[list.size()][16];
        
        for(int i=0;i<list.size();i++){
            
            int total =list.get(i).getRecursive()*recCup+list.get(i).getRegularRegular()*rrCup+list.get(i).getRegularRecursive()*rrecCup+list.get(i).getRecursiveRecursive()*recrecCup+list.get(i).getRecursiveRegular()*recrCup+list.get(i).getRegularGlobal()*rgCup+list.get(i).getRecursiveGlobal()*recgCup;
            
            rows[i][0] = list.get(i).getlNum();
            rows[i][1] = list.get(i).getLine();
            rows[i][2] = list.get(i).getRecursive();
            rows[i][3] = list.get(i).getRegularRegular();
            rows[i][4] = 0;
            rows[i][5] = list.get(i).getRegularRecursive();
            rows[i][6] = 0;
            rows[i][7] = list.get(i).getRecursiveRecursive();
            rows[i][8] = 0;
            rows[i][9] = list.get(i).getRecursiveRegular();
            rows[i][10] = 0;
            rows[i][11] = list.get(i).getRegularGlobal();
            rows[i][12] = 0;
            rows[i][13] = list.get(i).getRecursiveGlobal();
            rows[i][14] = 0;
            rows[i][15] = total;
            
        }
        
        TableModel tbm= new TableModel(rows,colNames);
        sizeTbl.setModel(tbm);
        sizeTbl.setRowHeight(20);
        sizeTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        sizeTbl.getColumnModel().getColumn(1).setPreferredWidth(400);
        sizeTbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(4).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(6).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(7).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(8).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(9).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(10).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(11).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(12).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(13).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(14).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(15).setPreferredWidth(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        sizeTbl.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
        sizeTbl.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(9).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(10).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(11).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(12).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(13).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(14).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(15).setCellRenderer( centerRenderer );
        
        ((DefaultTableCellRenderer)sizeTbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        sizeTbl.setShowGrid(true);
        sizeTbl.setGridColor(Color.black);
        sizeTbl.setSelectionBackground(Color.DARK_GRAY);
        
        JTableHeader th = sizeTbl.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.BOLD,15));
        
    }
    
    public void displayControl(String code, int ifz, int loopz, int swthz, int csez){
    
        ArrayList<Control> list = controlCalculate(code,ifz,loopz,swthz,csez);
        
        String[] colNames = {"Line no","Program statements","Wtcs","NC","Ccspps","Ccs"};
        Object[][]rows= new Object[list.size()][6];
        
        for(int i=0;i<list.size();i++){
            
            int Ccs = (list.get(i).getLineWeight()*list.get(i).getNoOfControl())+list.get(i).getCspps();
            
            rows[i][0] = list.get(i).getlNum();
            rows[i][1] = list.get(i).getLine();
            rows[i][2] = list.get(i).getLineWeight();
            rows[i][3] = list.get(i).getNoOfControl();
            rows[i][4] = list.get(i).getCspps();
            rows[i][5] = Ccs;
            
            
        }
        
        TableModel tbm= new TableModel(rows,colNames);
        sizeTbl.setModel(tbm);
        sizeTbl.setRowHeight(20);
        sizeTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        sizeTbl.getColumnModel().getColumn(1).setPreferredWidth(400);
        sizeTbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(4).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        sizeTbl.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
        sizeTbl.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        
        ((DefaultTableCellRenderer)sizeTbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        sizeTbl.setShowGrid(true);
        sizeTbl.setGridColor(Color.black);
        sizeTbl.setSelectionBackground(Color.DARK_GRAY);
        
        JTableHeader th = sizeTbl.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.BOLD,15));
        
    }
    
    public void displayAll(String code){
    
        ArrayList<size> list1 = sizeCalculate(code);
        
        ArrayList<Methods> list2 = methodCalculate(code);
        
        ArrayList<Variables> list3 = variableCalculate(code);
        
        ArrayList<Control> list4 = controlCalculate(code,2,3,2,1);
        
        ArrayList<Coupling> list5 = couplingCalculate(code);
        
        ArrayList<Inheritance> list6 = inheritanceCalculate(code);
        
        String[] colNames = {"Line no","Program statements","Cs","Cv","Cm","Ci","Ccp","Ccs","TCps"};
        Object[][]rows= new Object[list1.size()+1][9];
        
        int tCs = 0;
        
        int tCv = 0;
        
        int tCi = 0;
        
        int tCm = 0;
        
        int tCcp = 0;
        
        int tCcs = 0;
        
        for (int k = 0;  k<list6.size();k++) {
                int Ci=list6.get(k).getDirect()+list6.get(k).getIndirect();;
                if(Ci==0){
                    Ci=0;
                }else if(Ci==1){
                    Ci=1;
                }else if(Ci==2){
                    Ci=2;
                }else if(Ci==3){
                    Ci=3;
                }else if(Ci>4){
                    Ci=4;
                }
                tCi=tCi+Ci;
        }
        int tc=0;
        for(int i=0;i<list1.size();i++){
            
            int Cs = list1.get(i).getKeyword()+list1.get(i).getIdentifier()+list1.get(i).getOperator()+list1.get(i).getNumerical()+list1.get(i).getString_Count();
            
            int Ccs = (list4.get(i).getLineWeight()*list4.get(i).getNoOfControl())+list4.get(i).getCspps();
            
            int Wvs =list3.get(i).getGlobal()*2+list3.get(i).getLocal();
            
            int Npdtv = list3.get(i).getGlobal()+list3.get(i).getLocal();
            
            int Cv = Wvs*(Npdtv+list3.get(i).getComposite()+list3.get(i).getComposite()*2);
            
            int Wmrt =list2.get(i).getCompositeMethod()*2+list2.get(i).getPrimitiveMethod();
            
            int Cm = Wmrt+(list2.get(i).getPrimitiveParameters()+list2.get(i).getCompositeParameters()*2);
            
            int Ccp =list5.get(i).getRecursive()*2+list5.get(i).getRegularRegular()*2+list5.get(i).getRegularRecursive()*3+list5.get(i).getRecursiveRecursive()*4+list5.get(i).getRecursiveRegular()*3+list5.get(i).getRegularGlobal()+list5.get(i).getRecursiveGlobal();
            
            rows[i][0] = list1.get(i).getlNum();
            rows[i][1] = list1.get(i).getLine();
            rows[i][2] = Cs;
            rows[i][3] = Cv;
            rows[i][4] = Cm;
            rows[i][5] = 0;
            rows[i][6] = Ccp;
            rows[i][7] = Ccs;
            rows[i][8] = Cs+Cv+Cm+Ccp+Ccs;
            
            tCs= tCs+Cs;
            tCv= tCv+Cv;
            tCm= tCm+Cm;
            tCcp= tCcp+Ccp;
            tCcs= tCcs+Ccs;
            
            tc++;
            
        }
        
        rows[tc][0] = "#";
        rows[tc][1] = "Total ";
        rows[tc][2] = tCs;
        rows[tc][3] = tCv;
        rows[tc][4] = tCm;
        rows[tc][5] = tCi;
        rows[tc][6] = tCcp;
        rows[tc][7] = tCcs;
        rows[tc][8] = tCi+tCs+tCv+tCm+tCcp+tCcs;
        
        TableModel tbm= new TableModel(rows,colNames);
        sizeTbl.setModel(tbm);
        sizeTbl.setRowHeight(20);
        sizeTbl.getColumnModel().getColumn(0).setPreferredWidth(80);
        sizeTbl.getColumnModel().getColumn(1).setPreferredWidth(400);
        sizeTbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(4).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(5).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(6).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(7).setPreferredWidth(50);
        sizeTbl.getColumnModel().getColumn(8).setPreferredWidth(50);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        sizeTbl.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(1).setCellRenderer( leftRenderer );
        sizeTbl.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(6).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
        sizeTbl.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
        
        ((DefaultTableCellRenderer)sizeTbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        sizeTbl.setShowGrid(true);
        sizeTbl.setGridColor(Color.black);
        sizeTbl.setSelectionBackground(Color.DARK_GRAY);
        
        JTableHeader th = sizeTbl.getTableHeader();
        th.setForeground(Color.blue);
        th.setFont(new Font("Tahoma",Font.BOLD,15));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sample_code = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        allFac = new javax.swing.JToggleButton();
        jPanel7 = new javax.swing.JPanel();
        sizeBtn = new javax.swing.JToggleButton();
        VariableBtn = new javax.swing.JToggleButton();
        MethodsBtn = new javax.swing.JToggleButton();
        InheritanceBtn = new javax.swing.JToggleButton();
        CouplingBtn = new javax.swing.JToggleButton();
        ControlStructureBtn = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sizeTbl = new javax.swing.JTable();
        resultsLb = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtWeight = new javax.swing.JLabel();
        couplingPln = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jComboBox26 = new javax.swing.JComboBox<>();
        rrecCup = new javax.swing.JComboBox<>();
        jComboBox27 = new javax.swing.JComboBox<>();
        rrCup = new javax.swing.JComboBox<>();
        recCup = new javax.swing.JComboBox<>();
        jComboBox28 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        recrCup = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        jComboBox30 = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        recrecCup = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        rgCup = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jComboBox33 = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        recgCup = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        jComboBox35 = new javax.swing.JComboBox<>();
        inheritancePnl = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        incr1 = new javax.swing.JComboBox<>();
        incr0 = new javax.swing.JComboBox<>();
        incr3 = new javax.swing.JComboBox<>();
        incr4 = new javax.swing.JComboBox<>();
        incr2 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        methodPnl = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        comMthd = new javax.swing.JComboBox<>();
        pmtvMthd = new javax.swing.JComboBox<>();
        cparaMthd = new javax.swing.JComboBox<>();
        pparaMthd = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        variablePnl = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lclVar = new javax.swing.JComboBox<>();
        gblVar = new javax.swing.JComboBox<>();
        comVar = new javax.swing.JComboBox<>();
        pmtvVar = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        sizePnl = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        idfrSize = new javax.swing.JComboBox<>();
        kywrdSize = new javax.swing.JComboBox<>();
        nmrclSize = new javax.swing.JComboBox<>();
        strSize = new javax.swing.JComboBox<>();
        optrSize = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        controlStructurePnl = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        loopCst = new javax.swing.JComboBox<>();
        ifCst = new javax.swing.JComboBox<>();
        cseCst = new javax.swing.JComboBox<>();
        swttchCst = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        sample_code.setColumns(20);
        sample_code.setRows(5);
        jScrollPane1.setViewportView(sample_code);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Insert Code Here:");

        jPanel6.setBackground(new java.awt.Color(0, 153, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Upload");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Set Weight");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton13.setText("Calculate");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        allFac.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        allFac.setText("All The Factor");
        allFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allFacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(allFac, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(allFac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 153, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sizeBtn.setText("Size");
        sizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeBtnActionPerformed(evt);
            }
        });

        VariableBtn.setText("Variable");
        VariableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VariableBtnActionPerformed(evt);
            }
        });

        MethodsBtn.setText("Methods");
        MethodsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MethodsBtnActionPerformed(evt);
            }
        });

        InheritanceBtn.setText("Inheritance");
        InheritanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InheritanceBtnActionPerformed(evt);
            }
        });

        CouplingBtn.setText("Coupling");
        CouplingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CouplingBtnActionPerformed(evt);
            }
        });

        ControlStructureBtn.setText("Control Structure");
        ControlStructureBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ControlStructureBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sizeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(VariableBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MethodsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InheritanceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CouplingBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ControlStructureBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(VariableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MethodsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InheritanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CouplingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ControlStructureBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 78, 1180, 660));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Ajith  New  S", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Code Complexity Measuring Tool");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, -1));

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));

        sizeTbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sizeTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(sizeTbl);

        resultsLb.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        resultsLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultsLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(431, 431, 431))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(resultsLb, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1180, 670));

        jPanel5.setBackground(new java.awt.Color(0, 153, 204));

        txtWeight.setBackground(new java.awt.Color(153, 0, 0));
        txtWeight.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        couplingPln.setBackground(new java.awt.Color(255, 255, 255));
        couplingPln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Coupling Type");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Weight");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("A recursive call");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("<html>A regular method calling another regular method in the same file</html>");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("<html>A regular method calling another regular method in a different file</html>");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("<html>A regular method calling a recursive method in the same file</html>");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("<html>A regular method calling a recursive method in a different file</html>");

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setText("Calculate");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        rrecCup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        rrCup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        recCup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("<html>A recursive method calling a regular method in a different file</html>");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setText("<html>A recursive method calling a regular method in the same file</html>");

        recrCup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("<html>A recursive method calling another recursive method in a different file</html>");

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText("<html>A recursive method calling another recursive method in the same file</html>");

        recrecCup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setText("<html>A regular method referencing a global variable in the same file</html>");

        rgCup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setText("<html>A regular method referencing a global variable in a different file</html>");

        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("<html>A recursive method referencing a global variable in the same file</html>");

        recgCup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("<html>A recursive method referencing a global variable in a different file</html>");

        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126)
                                .addComponent(recCup, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(rrCup, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rrecCup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(recrecCup, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(recrCup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rgCup, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(recgCup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(recCup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(rrCup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(rrecCup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(recrecCup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(recrCup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(rgCup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jComboBox33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(recgCup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel4);

        javax.swing.GroupLayout couplingPlnLayout = new javax.swing.GroupLayout(couplingPln);
        couplingPln.setLayout(couplingPlnLayout);
        couplingPlnLayout.setHorizontalGroup(
            couplingPlnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(couplingPlnLayout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, couplingPlnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        couplingPlnLayout.setVerticalGroup(
            couplingPlnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(couplingPlnLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(couplingPlnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        inheritancePnl.setBackground(new java.awt.Color(255, 255, 255));
        inheritancePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Inherited Pattern");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Weight");

        incr1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        incr0.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        incr3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        incr4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        incr2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("<html>A class with no Inheritance(direct or indirect)</html>");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("<html>A class inheriting(direct or indirect) from one user-defined class<html>");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("<html>A class inheriting(direct or indirect) from two user-defined classes<html>");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("<html>A class inheriting(direct or indirect) from three user-defined classes<html>");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("<html>A class inheriting(direct or indirect) from more than three user-defined classes<html>");

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setText("Calculate");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inheritancePnlLayout = new javax.swing.GroupLayout(inheritancePnl);
        inheritancePnl.setLayout(inheritancePnlLayout);
        inheritancePnlLayout.setHorizontalGroup(
            inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inheritancePnlLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inheritancePnlLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(inheritancePnlLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(incr0, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(inheritancePnlLayout.createSequentialGroup()
                        .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inheritancePnlLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(inheritancePnlLayout.createSequentialGroup()
                                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(inheritancePnlLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10))
                            .addGroup(inheritancePnlLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(incr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(incr2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(incr3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(incr4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(70, 70, 70))
        );
        inheritancePnlLayout.setVerticalGroup(
            inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inheritancePnlLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(incr0, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(incr1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(incr2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(inheritancePnlLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(incr3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(inheritancePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(incr4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        methodPnl.setBackground(new java.awt.Color(255, 255, 255));
        methodPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        methodPnl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Program Component");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Weight");

        comMthd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        pmtvMthd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        cparaMthd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        pparaMthd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Method with a primitive return type");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Method with a composite return type");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Primitive data type parameter");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Composite data type parameter");

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setText("Calculate");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout methodPnlLayout = new javax.swing.GroupLayout(methodPnl);
        methodPnl.setLayout(methodPnlLayout);
        methodPnlLayout.setHorizontalGroup(
            methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(methodPnlLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, methodPnlLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(methodPnlLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pmtvMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(methodPnlLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(methodPnlLayout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pparaMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(methodPnlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(methodPnlLayout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cparaMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );
        methodPnlLayout.setVerticalGroup(
            methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(methodPnlLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pmtvMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pparaMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(methodPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cparaMthd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        variablePnl.setBackground(new java.awt.Color(255, 255, 255));
        variablePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        variablePnl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Program Component");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Weight");

        lclVar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        gblVar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        comVar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        pmtvVar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Global Variable");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Local Variable");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Primitive Data Type Variable");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Composite Data Type Variable");

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setText("Calculate");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout variablePnlLayout = new javax.swing.GroupLayout(variablePnl);
        variablePnl.setLayout(variablePnlLayout);
        variablePnlLayout.setHorizontalGroup(
            variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variablePnlLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, variablePnlLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(variablePnlLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gblVar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(variablePnlLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lclVar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(variablePnlLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pmtvVar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(variablePnlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(variablePnlLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comVar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );
        variablePnlLayout.setVerticalGroup(
            variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variablePnlLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gblVar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lclVar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pmtvVar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(variablePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comVar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        sizePnl.setBackground(new java.awt.Color(255, 255, 255));
        sizePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Program Component");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Weight");

        idfrSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        kywrdSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        nmrclSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        strSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        optrSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Keyword");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Identifier");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Operator");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Numerical Value");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("String Iiteral");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("Calculate");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sizePnlLayout = new javax.swing.GroupLayout(sizePnl);
        sizePnl.setLayout(sizePnlLayout);
        sizePnlLayout.setHorizontalGroup(
            sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sizePnlLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sizePnlLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sizePnlLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nmrclSize, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sizePnlLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(optrSize, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sizePnlLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idfrSize, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sizePnlLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kywrdSize, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sizePnlLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(strSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(70, 70, 70))
        );
        sizePnlLayout.setVerticalGroup(
            sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sizePnlLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kywrdSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idfrSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optrSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nmrclSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(sizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        controlStructurePnl.setBackground(new java.awt.Color(255, 255, 255));
        controlStructurePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Control Structure Type");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Weight");

        loopCst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        ifCst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        cseCst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        swttchCst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("<html>A conditional control structure such as an 'if' or 'else-if' condition</html>");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("<html>An iterative control structure such as an 'for' , 'while' , or 'do-while' loop</html>");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("<html>The 'switch' statement in a 'switch-case' control structure</html>");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("<html>Each 'case' statement in a 'switch-case' control structure</html>");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("Calculate");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlStructurePnlLayout = new javax.swing.GroupLayout(controlStructurePnl);
        controlStructurePnl.setLayout(controlStructurePnlLayout);
        controlStructurePnlLayout.setHorizontalGroup(
            controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlStructurePnlLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlStructurePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(controlStructurePnlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, controlStructurePnlLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlStructurePnlLayout.createSequentialGroup()
                                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ifCst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(swttchCst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(controlStructurePnlLayout.createSequentialGroup()
                                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(47, 47, 47)
                                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loopCst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cseCst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(70, 70, 70))
        );
        controlStructurePnlLayout.setVerticalGroup(
            controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlStructurePnlLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ifCst, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loopCst, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(swttchCst, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(controlStructurePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cseCst, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(461, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtWeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(controlStructurePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(514, Short.MAX_VALUE)
                    .addComponent(sizePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(515, Short.MAX_VALUE)
                    .addComponent(variablePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(515, Short.MAX_VALUE)
                    .addComponent(methodPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(515, Short.MAX_VALUE)
                    .addComponent(inheritancePnl, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(515, Short.MAX_VALUE)
                    .addComponent(couplingPln, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(controlStructurePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addComponent(sizePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(66, 66, 66)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(97, Short.MAX_VALUE)
                    .addComponent(variablePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(66, 66, 66)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(97, Short.MAX_VALUE)
                    .addComponent(methodPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(66, 66, 66)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addComponent(inheritancePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(65, 65, 65)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(93, Short.MAX_VALUE)
                    .addComponent(couplingPln, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(64, 64, 64)))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 65, 1180, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeBtnActionPerformed
        disableBtn();
        sizeBtn.setSelected(true);
    }//GEN-LAST:event_sizeBtnActionPerformed

    private void VariableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VariableBtnActionPerformed
        disableBtn();
        VariableBtn.setSelected(true);
    }//GEN-LAST:event_VariableBtnActionPerformed

    private void MethodsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MethodsBtnActionPerformed
        disableBtn();
        MethodsBtn.setSelected(true);
    }//GEN-LAST:event_MethodsBtnActionPerformed

    private void InheritanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InheritanceBtnActionPerformed
        disableBtn();
        InheritanceBtn.setSelected(true);
    }//GEN-LAST:event_InheritanceBtnActionPerformed

    private void CouplingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CouplingBtnActionPerformed
        disableBtn();
        CouplingBtn.setSelected(true);
    }//GEN-LAST:event_CouplingBtnActionPerformed

    private void ControlStructureBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ControlStructureBtnActionPerformed
        disableBtn();
        ControlStructureBtn.setSelected(true);
    }//GEN-LAST:event_ControlStructureBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")+"/Desktop"));
        
        FileNameExtensionFilter filefilter = new FileNameExtensionFilter("*.java","java");
        file.addChoosableFileFilter(filefilter);
        
        int filestate = file.showSaveDialog(null);
        
        if(filestate == JFileChooser.APPROVE_OPTION){
            
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            
            File myhtml = new File(path);
        
            try {
                String str = FileUtils.readFileToString(myhtml);
                
                sample_code.setText(str);
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
                    
        }else if(filestate == JFileChooser.CANCEL_OPTION){
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        sample_code.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if(CouplingBtn.isSelected()||ControlStructureBtn.isSelected()||InheritanceBtn.isSelected()||MethodsBtn.isSelected()||VariableBtn.isSelected()||sizeBtn.isSelected()||allFac.isSelected()){
            if(!sample_code.getText().equals("")){
                jPanel1.setVisible(false);
                jPanel3.setVisible(false);
                jPanel5.setVisible(true);
                pnlDisable();
                if(CouplingBtn.isSelected()){
                    couplingPln.setVisible(true);
                    txtWeight.setText("Weight Related To The Coupling Factor");
                }else if(ControlStructureBtn.isSelected()){
                    controlStructurePnl.setVisible(true);
                    txtWeight.setText("Weight Related To The Control Structure Factor");
                }else if(InheritanceBtn.isSelected()){
                    inheritancePnl.setVisible(true);
                    txtWeight.setText("Weight Related To The Inheritance Factor");
                }else if(MethodsBtn.isSelected()){
                    methodPnl.setVisible(true);
                    txtWeight.setText("Weight Related To The Method Factor");
                }else if(VariableBtn.isSelected()){
                    variablePnl.setVisible(true);
                    txtWeight.setText("Weight Related To The Variable Factor");
                }else if(sizeBtn.isSelected()){
                    sizePnl.setVisible(true);
                    txtWeight.setText("Weight Related To The Size Factor");
                }else if(allFac.isSelected()){
                    jPanel3.setVisible(true);
                    displayAll(sample_code.getText());
                    resultsLb.setText("All The Factor");
                }else{
                    jPanel1.setVisible(true);
                    jPanel3.setVisible(false);
                    jPanel5.setVisible(false);
                }
            }else{
                JOptionPane.showMessageDialog(new JFrame(),"Sample Code Required!");
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Please Select Calculate Type!");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jPanel3.setVisible(false);
        jPanel1.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jPanel1.setVisible(true);
        jPanel3.setVisible(false);
        jPanel5.setVisible(false);
        pnlDisable();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        pnlDisable();
        jPanel5.setVisible(false);
        displaySize(sample_code.getText(),Integer.parseInt(kywrdSize.getSelectedItem().toString()),Integer.parseInt(idfrSize.getSelectedItem().toString()),Integer.parseInt(optrSize.getSelectedItem().toString()),Integer.parseInt(nmrclSize.getSelectedItem().toString()),Integer.parseInt(strSize.getSelectedItem().toString()));
        resultsLb.setText("Size");
        jPanel3.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        pnlDisable();
        jPanel5.setVisible(false);
        jPanel3.setVisible(true);
        displayVariable(sample_code.getText(),Integer.parseInt(gblVar.getSelectedItem().toString()),Integer.parseInt(lclVar.getSelectedItem().toString()),Integer.parseInt(pmtvVar.getSelectedItem().toString()),Integer.parseInt(comVar.getSelectedItem().toString()));
        resultsLb.setText("Variables");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        pnlDisable();
        jPanel5.setVisible(false);
        jPanel3.setVisible(true);
        displayMethod(sample_code.getText(),Integer.parseInt(pmtvMthd.getSelectedItem().toString()),Integer.parseInt(comMthd.getSelectedItem().toString()),Integer.parseInt(pparaMthd.getSelectedItem().toString()),Integer.parseInt(cparaMthd.getSelectedItem().toString()));
        resultsLb.setText("Methods");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        pnlDisable();
        jPanel5.setVisible(false);
        jPanel3.setVisible(true);
        displayControl(sample_code.getText(),Integer.parseInt(ifCst.getSelectedItem().toString()),Integer.parseInt(loopCst.getSelectedItem().toString()),Integer.parseInt(swttchCst.getSelectedItem().toString()),Integer.parseInt(cseCst.getSelectedItem().toString()));
        resultsLb.setText("Control structures");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        pnlDisable();
        jPanel5.setVisible(false);
        jPanel3.setVisible(true);
        displayInheritance(sample_code.getText(),Integer.parseInt(incr0.getSelectedItem().toString()),Integer.parseInt(incr1.getSelectedItem().toString()),Integer.parseInt(incr2.getSelectedItem().toString()),Integer.parseInt(incr3.getSelectedItem().toString()),Integer.parseInt(incr4.getSelectedItem().toString()));
        resultsLb.setText("Inheritance");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        pnlDisable();
        jPanel5.setVisible(false);
        jPanel3.setVisible(true);
        displayCoupling(sample_code.getText(),Integer.parseInt(recCup.getSelectedItem().toString()),Integer.parseInt(rrCup.getSelectedItem().toString()),Integer.parseInt(rrecCup.getSelectedItem().toString()),Integer.parseInt(recrecCup.getSelectedItem().toString()),Integer.parseInt(recrCup.getSelectedItem().toString()),Integer.parseInt(rgCup.getSelectedItem().toString()),Integer.parseInt(recgCup.getSelectedItem().toString()));
        resultsLb.setText("Coupling");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(CouplingBtn.isSelected()||ControlStructureBtn.isSelected()||InheritanceBtn.isSelected()||MethodsBtn.isSelected()||VariableBtn.isSelected()||sizeBtn.isSelected()||allFac.isSelected()){
            if(!sample_code.getText().equals("")){
                jPanel1.setVisible(false);
                jPanel3.setVisible(true);
                if(CouplingBtn.isSelected()){
                    displayCoupling(sample_code.getText(),2,2,3,4,3,1,1);
                    resultsLb.setText("Coupling");
                }else if(ControlStructureBtn.isSelected()){
                    displayControl(sample_code.getText(),2,3,2,1);
                    resultsLb.setText("Control structures");
                }else if(InheritanceBtn.isSelected()){
                    displayInheritance(sample_code.getText(),0,1,2,3,4);
                    resultsLb.setText("Inheritance");
                }else if(MethodsBtn.isSelected()){
                    displayMethod(sample_code.getText(),1,2,1,2);
                    resultsLb.setText("Methods");
                }else if(VariableBtn.isSelected()){
                    displayVariable(sample_code.getText(),2,1,1,2);
                    resultsLb.setText("Variables");
                }else if(sizeBtn.isSelected()){
                    displaySize(sample_code.getText(),1,1,1,1,1);
                    resultsLb.setText("Size");
                }else if(allFac.isSelected()){
                    displayAll(sample_code.getText());
                    resultsLb.setText("All The Factor");
                }else{
                    jPanel1.setVisible(true);
                    jPanel3.setVisible(false);
                }
            }else{
                JOptionPane.showMessageDialog(new JFrame(),"Sample Code Required!");
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Please Select Calculate Type!");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void allFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allFacActionPerformed
        disableBtn();
        allFac.setSelected(true);
    }//GEN-LAST:event_allFacActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ControlStructureBtn;
    private javax.swing.JToggleButton CouplingBtn;
    private javax.swing.JToggleButton InheritanceBtn;
    private javax.swing.JToggleButton MethodsBtn;
    private javax.swing.JToggleButton VariableBtn;
    private javax.swing.JToggleButton allFac;
    private javax.swing.JComboBox<String> comMthd;
    private javax.swing.JComboBox<String> comVar;
    private javax.swing.JPanel controlStructurePnl;
    private javax.swing.JPanel couplingPln;
    private javax.swing.JComboBox<String> cparaMthd;
    private javax.swing.JComboBox<String> cseCst;
    private javax.swing.JComboBox<String> gblVar;
    private javax.swing.JComboBox<String> idfrSize;
    private javax.swing.JComboBox<String> ifCst;
    private javax.swing.JComboBox<String> incr0;
    private javax.swing.JComboBox<String> incr1;
    private javax.swing.JComboBox<String> incr2;
    private javax.swing.JComboBox<String> incr3;
    private javax.swing.JComboBox<String> incr4;
    private javax.swing.JPanel inheritancePnl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox26;
    private javax.swing.JComboBox<String> jComboBox27;
    private javax.swing.JComboBox<String> jComboBox28;
    private javax.swing.JComboBox<String> jComboBox30;
    private javax.swing.JComboBox<String> jComboBox33;
    private javax.swing.JComboBox<String> jComboBox35;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> kywrdSize;
    private javax.swing.JComboBox<String> lclVar;
    private javax.swing.JComboBox<String> loopCst;
    private javax.swing.JPanel methodPnl;
    private javax.swing.JComboBox<String> nmrclSize;
    private javax.swing.JComboBox<String> optrSize;
    private javax.swing.JComboBox<String> pmtvMthd;
    private javax.swing.JComboBox<String> pmtvVar;
    private javax.swing.JComboBox<String> pparaMthd;
    private javax.swing.JComboBox<String> recCup;
    private javax.swing.JComboBox<String> recgCup;
    private javax.swing.JComboBox<String> recrCup;
    private javax.swing.JComboBox<String> recrecCup;
    private javax.swing.JLabel resultsLb;
    private javax.swing.JComboBox<String> rgCup;
    private javax.swing.JComboBox<String> rrCup;
    private javax.swing.JComboBox<String> rrecCup;
    private javax.swing.JTextArea sample_code;
    private javax.swing.JToggleButton sizeBtn;
    private javax.swing.JPanel sizePnl;
    private javax.swing.JTable sizeTbl;
    private javax.swing.JComboBox<String> strSize;
    private javax.swing.JComboBox<String> swttchCst;
    private javax.swing.JLabel txtWeight;
    private javax.swing.JPanel variablePnl;
    // End of variables declaration//GEN-END:variables
}
