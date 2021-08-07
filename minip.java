
package mini_p;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;



class node
{
	String name;
	String category;
	String genre;
	String Description;
	String Language;
	float rating;

	int h;
	node left;
	node right;

	node(String name,String category,String genre, String Description, String Language,float rating)
	{
		this.name=name;
		this.category=category;
		this.genre=genre;
		this.Description=Description;
		this.Language=Language;
		this.rating=rating;
		h=0;
		left=null;
		right=null;
	}
}
class tree
{
	node root;


	node rr(node root)  //left rotation
	{
		System.out.println("============================================");
		levelOrder(this.root);
		System.out.println("============================================");
		node ptr,temp;
		ptr=root;
		temp=ptr.right;
		ptr.right=temp.left;
		temp.left=ptr;
		temp.h=height(temp);
		ptr.h=height(temp);
		System.out.println("RR is done");
		return temp;
	}

	node ll(node root)   //right rotation

	{
		System.out.println("============================================");
		levelOrder(this.root);
		System.out.println("============================================");
		node ptr,temp;
		ptr=root;
		temp=ptr.left;
		ptr.left=temp.right;
		temp.right=ptr;
		temp.h=height(temp);
		ptr.h=height(temp);
		System.out.println("LL is done");

		return(temp);		//returning the value temp
	}

	node lr(node root)
	{
		System.out.println("============================================");
		levelOrder(this.root);
		System.out.println("============================================");
		root.left=rr(root.left);
		root=ll(root);
		//System.out.println("LR is required");

		return root;
	}

	node rl(node root)
	{
		System.out.println("============================================");
		levelOrder(this.root);
		System.out.println("============================================");
		root.right=ll(root.right);
		root=rr(root);
		//System.out.println("RL is required");

		return root;
	}


	int height(node t)
	{

		if(t==null)
		{
			return 0;
		}
		else
		{
			int l_h=height(t.left);
			int r_h=height(t.right);
			if(l_h>r_h)
			{
				return (l_h+1);

			}
			else
			{
				return (r_h+1);
			}
		}
	}

	node add(node localroot,String nm,String cate,String gr, String des,String lang,float rate)
	{

		int bal=0;

		if (localroot == null) 
		{
			return new node(nm,cate,gr,des,lang,rate);
		} 
		else 
		{

			if (localroot.name.compareToIgnoreCase(nm) < 0)
			{
				localroot.right = add(localroot.right, nm,cate,gr,des,lang,rate);
			}
			else if (localroot.name.compareToIgnoreCase(nm) > 0)
			{
				localroot.left = add(localroot.left,nm,cate, gr,des,lang,rate);
			}

		}

		int bf = bf(localroot);
		if (bf > 1) {
			if (nm.compareToIgnoreCase(localroot.left.name) < 0)
			{
				//levelOrder(root);
				System.out.println("Balance factor of "+localroot.name+" is "+bf);
				return ll(localroot);
			}
			else
			{
				
				System.out.println("LR required");
				System.out.println("Balance factor of "+localroot.name+" is "+bf);
				return lr(localroot);
			}
		} else if (bf < -1)
		{
			if (nm.compareToIgnoreCase(localroot.right.name) > 0)
			{
				

				System.out.println("Balance factor of "+localroot.name+" is "+bf);
				return rr(localroot);
			}
			else
			{
				System.out.println("RL required");
				System.out.println("Balance factor of "+localroot.name+" is "+bf);
				return rl(localroot);
			}
		}

		return localroot;

	}

	/*int max(int a ,int b)
{
if(a>b)
return a;
else return b;
}*/

	void display(node lroot) {   //display alphabetically
		if (lroot != null) {
			display(lroot.left);
			System.out.println(lroot.name + " ");
			System.out.println(lroot.category+ " ");
			System.out.println(lroot.genre + " ");
			System.out.println(lroot.Description + " ");
			System.out.println(lroot.Language + " ");
			System.out.println(lroot.rating + " ");
			System.out.println();
			display(lroot.right);
		}
	}
	void levelOrder(node n) //print tree levelwise
	{
		Queue<node> q = new LinkedList<node>();
		q.add(n);

		while(true)
		{
			int c=q.size();
			if(c==0)
				break;
			while(c>0)
			{ //display alphabetically
				n=q.remove();
				System.out.print(" "+n.name +" ");
				System.out.print(" "+n.genre +" ");
				System.out.print(" "+n.Description +" ");
				System.out.print(" "+n.Language +" ");
				System.out.print(" "+n.rating +" ");
				if(n.left!=null)
					q.add(n.left);
				if(n.right!=null)
					q.add(n.right);
				if(q.isEmpty())
				{
					n=null;
				}
				c--;

			}
			System.out.println();

		}
	}

	int bf(node root2) 
	{


		return height(root2.left)-height(root2.right);
	}

	void search_by_name(node localroot) {
		Scanner sc = new Scanner(System.in);
		node ptr;
		String n;
		int flag = 0;
		System.out.println("Enter the name of you want to search: ");
		n = sc.next();

		ptr = localroot;
		while (ptr != null)
		{
			if ((n).compareToIgnoreCase(ptr.name) == 0) //
			{
				flag = 1;
				break;
			} else if ((n).compareToIgnoreCase(ptr.name) < 0)// move to left
			{
				ptr = ptr.left;
			} else {
				ptr = ptr.right;// move to right
			}
		}
		if (flag == 0)
		{
			System.out.println("No data found !!!");
		}
		else 
		{
			System.out.println(" HOPE YOU WOULD ENJOY THE SHOW ");
			
			System.out.println("Name of the show :"+ptr.name);

			System.out.println("Category of the show is :" +ptr.category);

			System.out.println("Genre of the show is: "+ptr.genre);

			System.out.println("Enter the Description of the show :"+ptr.Description);

			System.out.println("Enter the langauge of the show :"+ptr.Language);

			System.out.println("Enter the rating of the show :"+ptr.rating);


		}

	}
	void search_by_category(node localroot) 
	{
		Scanner sc = new Scanner(System.in);
		node ptr;
		String c;
		int flag = 0;
		System.out.println("Enter the category of the show: ");
		c = sc.nextLine();

		ptr = localroot;
		while (ptr != null) {
			if ((c).compareToIgnoreCase(ptr.category) == 0) //
			{
				flag = 1;
				break;
			} else if ((c).compareToIgnoreCase(ptr.category) < 0)// move to left
			{
				ptr = ptr.left;
			} else {
				ptr = ptr.right;// move to right
			}
		}
		if (flag == 0)
		{
			System.out.println("No data found !!!");
		} 
		else 
		{
			System.out.println(" HOPE YOU WOULD ENJOY THE SHOW ");
			
			System.out.println("Name of the show :"+ptr.name);

			System.out.println("Category of the show is :" +ptr.category);
			
			System.out.println("Genre of the show is: "+ptr.genre);
			
			System.out.println("Enter the Description of the show :"+ptr.Description);
			
			System.out.println("Enter the langauge of the show :"+ptr.Language);
			
			System.out.println("Enter the rating of the show :"+ptr.rating);
			
		}

	}

	void search_by_language(node localroot) {
		Scanner sc = new Scanner(System.in);
		node ptr;
		String l;
		int flag = 0;
		System.out.println("Enter the name of you want to search: ");
		l = sc.next();

		ptr = localroot;
		while (ptr != null)
		{
			if ((l).compareToIgnoreCase(ptr.Language) == 0) //
			{
				flag = 1;
				break;
			} else if ((l).compareToIgnoreCase(ptr.Language) < 0)// move to left
			{
				ptr = ptr.left;
			} else {
				ptr = ptr.right;// move to right
			}
		}
		if (flag == 0)
		{
			System.out.println("No data found !!!");
		}
		else 
		{
			System.out.println(" HOPE YOU WOULD ENJOY THE SHOW ");
			
			System.out.println("Name of the show :"+ptr.name);

			System.out.println("Category of the show is :" +ptr.category);

			System.out.println("Genre of the show is: "+ptr.genre);

			System.out.println("Enter the Description of the show :"+ptr.Description);

			System.out.println("Enter the langauge of the show :"+ptr.Language);

			System.out.println("Enter the rating of the show :"+ptr.rating);


		}

	}
	void search_by_genre(node localroot)
	{
		Scanner sc = new Scanner(System.in);
		node ptr;
		String g;
		int flag = 0;
		System.out.println("Enter the name of you want to search: ");
		g= sc.next();

		ptr = localroot;
		while (ptr != null)
		{
			if ((g).compareToIgnoreCase(ptr.genre) == 0) //
			{
				flag = 1;
				break;
			} else if ((g).compareToIgnoreCase(ptr.genre) < 0)// move to left
			{
				ptr = ptr.left;
			} else {
				ptr = ptr.right;// move to right
			}
		}
		if (flag == 0)
		{
			System.out.println("No data found !!!");
		}
		else 
		{
			System.out.println(" HOPE YOU WOULD ENJOY THE SHOW ");
			
			System.out.println("Name of the show :"+ptr.name);

			System.out.println("Category of the show is :" +ptr.category);

			System.out.println("Genre of the show is: "+ptr.genre);

			System.out.println("Enter the Description of the show :"+ptr.Description);

			System.out.println("Enter the langauge of the show :"+ptr.Language);

			System.out.println("Enter the rating of the show :"+ptr.rating);


		}

	}

	void search_by_rating(node localroot)
	{
		Scanner sc = new Scanner(System.in);
		node ptr;
		float r;
		int flag = 0;
		System.out.println("Enter the name of you want to search: ");
		r= sc.nextFloat();

		ptr = localroot;
		while (ptr != null)
		{
			if ((r)==(ptr.rating) ) //
			{
				flag = 1;
				break;
			} else if ((r) < (ptr.rating))// move to left
			{
				ptr = ptr.left;
			} else {
				ptr = ptr.right;// move to right
			}
		}
		if (flag == 0)
		{
			System.out.println("No data found !!!");
		}
		else 
		{
			System.out.println(" HOPE YOU WOULD ENJOY HTE SHOW ");
			
			System.out.println("Name of the show :"+ptr.name);

			System.out.println("Category of the show is :" +ptr.category);

			System.out.println("Genre of the show is: "+ptr.genre);

			System.out.println("Enter the Description of the show :"+ptr.Description);

			System.out.println("Enter the langauge of the show :"+ptr.Language);

			System.out.println("Enter the rating of the show :"+ptr.rating);


		}

	}
	void create()
	{
		char ch=0;
		String nme;
		String cat;
		String gen;
		String desp;
		String lan;
		float rt;
		do
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the name of the show :");
			nme=sc.nextLine();
			System.out.println("Enter the category of he show :");
			cat=sc.nextLine();
			System.out.println("Enter the genre of the show ");
			gen=sc.nextLine();
			System.out.println("Enter the Description of the show :");
			desp=sc.nextLine();
			System.out.println("Enter the langauge of the show :");
			lan=sc.nextLine();
			System.out.println("Enter the rating of the show :");
			rt=sc.nextFloat();
			root= add(root,nme,cat,gen,desp,lan,rt);
			System.out.println("============================================");
			levelOrder(root);
			System.out.println("============================================");

			System.out.println("Do you want to enter more Shows? ");
			ch=sc.next().charAt(0);


		}while(ch=='y'||ch=='Y');
	}
}

public class minip 
{

	public static void main(String args[])
	{
		tree p= new tree();
		int ch1;
		int ch11;
		int ch12=0;
		int ch21=0;
		String nm;
		String cate;
		String gr;
		String des;
		String lang;
		float rate;
		do 
		{
			System.out.println("1.Upload movies");
			System.out.println("2.Watch movies");
			Scanner sc= new Scanner(System.in);
			ch1=sc.nextInt();
			switch(ch1)
			{
			case 1: do {
				System.out.println("1.Create database");
				System.out.println("2.Insert an entry");
				System.out.println("3. display");
				System.out.println("4.exit");
				ch11=sc.nextInt();
				switch(ch11)
				{
				case 1:
					p.create();
					break;
				case 2:
					if(p!=null)
					{
						Scanner sc1=new Scanner(System.in);
						System.out.println("Enter the name of the show :");
						nm=sc1.nextLine();
						System.out.println("Enter the category of the show :");
						cate=sc1.nextLine();
						System.out.println("Enter the genre of the show ");
						gr=sc1.nextLine();
						System.out.println("Enter the Description of the show :");
						des=sc1.nextLine();
						System.out.println("Enter the langauge of the show :");
						lang=sc1.nextLine();
						System.out.println("Enter the rating of the show :");
						rate=sc1.nextFloat();
						p.add(p.root, nm, cate, gr, des, lang, rate);
					}
				else 
					{
						System.out.println("Database has not been created");
					}
				break;
				case 3:
					if(p!=null)
					{
						p.display(p.root);
					}
					else
					{
						System.out.println("Database has not been created.");
					}
					System.out.println("============================================");
					break;
				
					

				}

			}while(ch11!=4);
			break;

			case 2:do {
				System.out.println("WELCOME TO FILM&CHILL!!! ");
				System.out.println("What would you like to watch?!");
				System.out.println("1.Have a look over the Content List");
				System.out.println("2.Choose how would you like to search?! ");
				ch12=sc.nextInt();
				switch(ch12) {
				case 1:if(p!=null)
				{
					p.display(p.root);
				}
				break;
				case 2:do 
				{
					System.out.println("WELCOME TO FILM&CHILL!!! ");
					System.out.println("*******************************************");
					System.out.println("1.Search by Name!");
					System.out.println("2.Search by Category!");
					System.out.println("3.Search by Language!");
					System.out.println("4.Search by Genre!");
					System.out.println("5.Search by rating!");
					ch21=sc.nextInt();
					switch(ch21)
					{
					case 1:System.out.println("============================================");
					if(p!=null)
					{
						p.search_by_name(p.root);
					}
					else
					{
						System.out.println("Database has not been created.");
					}
					System.out.println("============================================");
					break;
					case 2:
						System.out.println("============================================");
					if(p!=null)
					{
						p.search_by_category(p.root);
					}
					else
					{
						System.out.println("Tree has not been created.");
					}
					System.out.println("============================================");
					break;
					
					case 3:
						System.out.println("==========================================");
						if(p!=null)
						{
							p.search_by_language(p.root);
						}
						else
						{
							System.out.println("Tree has not been created.");
						}
						System.out.println("============================================");
						break;

					case 4:
						System.out.println("==========================================");
						if(p!=null)
						{
							p.search_by_genre(p.root);
						}
						else
						{
							System.out.println("Tree has not been created.");
						}
						System.out.println("============================================");
						break;
					case 5:
						System.out.println("==========================================");
						if(p!=null)
						{
							p.search_by_rating(p.root);
						}
						else
						{
							System.out.println("Tree has not been created.");
						}
						System.out.println("============================================");
						break;
					case 6:
						System.out.println("ENTER THE VALID OIPTION !!!");

					}

					
						
				}while(ch21!=0);
				break;
				}

			}while(ch12!=4);
			}

		}while(ch1!=3);

	}
}




