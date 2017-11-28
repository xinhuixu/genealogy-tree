/* FILE NAME: MyGenealogy.java
 * WHO: Xinhui Xu
 * WHEN: Fall 2017
 * WHAT: Assignment 8 Task 2
*/

public class MyGenealogy{

    public static void main(String[] args){
	System.out.println("My fictional genealogy tree.\n My ancestors all have numerical code names, as they were secret agents.");
	GenealogyTree<String> t2 = new GenealogyTree<String>("Xinhui");
	try{
	    t2.setPater("Xinhui", "1");
	    t2.setMater("Xinhui", "2");
	    t2.setPater("1", "3"); t2.setMater("1", "4");
	    t2.setPater("2", "5"); t2.setMater("2", "6");
	    t2.setPater("3", "7"); t2.setMater("3", "8");
	    t2.setPater("4", "9"); t2.setMater("4", "10");
	    t2.setPater("5", "11"); t2.setMater("5", "12");
	    t2.setPater("6", "13"); t2.setMater("6", "14");
	} catch (Exception ex){
	    System.out.println(ex);
	}
	System.out.println(t2);
	System.out.println("13 was found (true): "+t2.appears("13"));
	System.out.println("Offspring of 5 is (2): "+t2.getOffspring("5"));
	System.out.println("Xinhui appear at the root: "+t2.getCoTU().equals("Xinhui"));
	System.out.println("Your paternal grandfather is (3): "+t2.getPater(t2.getPater("Xinhui")));
	System.out.println("Your maternal grandmother is (6): "+t2.getMater(t2.getMater("Xinhui")));
	System.out.println("Your paternal great-grandfather is (7): "+t2.getPater(t2.getPater(t2.getPater("Xinhui"))));
	System.out.println("Your maternal great-grandmother is (14): "+t2.getMater(t2.getMater(t2.getMater("Xinhui"))));
	System.out.println("3 and 5 are in-laws (true):"+t2.inLaws("3","5"));
	System.out.println("3 and 2 are in-laws (false):"+t2.inLaws("3","2"));	  
    }


}
