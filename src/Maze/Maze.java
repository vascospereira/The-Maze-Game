package Maze;

import java.util.Scanner;

public class Maze {

	public static void main(String[] args) 
	{
		//Creating Table
		Table table = new Table();

		//Creating a new Hero
		Hero hero = new Hero();
		hero.HeroDeploy(table);

		//Creating a new Dragon
		Dragon dragon = new Dragon();
		dragon.DragonDeploy(table);

		//Creating a new Sword
		Sword sword = new Sword();
		sword.SwordDeploy(table);
		
		//Printing Table
		table.PrintTable();
		
		//INITIALIZING SCANNER
		Scanner s = new Scanner(System.in);
		char c = s.next().charAt(0);
		
		while(c != 'F' && c != 'f')
		{
			if(c == 'W' || c == 'w')
			{
				System.out.println("Up");
				hero.HeroMoveUp(table);
				table.PrintTable();
			}
			if(c == 'A' || c == 'a')
			{
				System.out.println("Left");
				hero.HeroMoveLeft(table);
				table.PrintTable();
			}
			if(c == 'S' || c == 's')
			{
				System.out.println("Down");
				hero.HeroMoveDown(table);
				table.PrintTable();
			}
			if(c == 'D' || c == 'd')
			{
				System.out.println("Right");
				hero.HeroMoveRight(table);
				table.PrintTable();
			}
			c = s.next().charAt(0);
		}
		
		//Closing Scanner and Returning
		s.close();
		return;
	}

}