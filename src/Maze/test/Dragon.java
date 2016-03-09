package Maze.test;

import static org.junit.Assert.*;
import Maze.logic.*;
import Maze.logic.Maze.State;

public class Dragon 
{
	@org.junit.Test (timeout=1000)
	public void DragonToEmptySpace() 
	{
		char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
				{ 'X', ' ', ' ', 'H', 'E' }, 
				{ 'X', ' ', 'X', ' ', 'X' },
				{ 'X', 'S', ' ', 'D', 'X' }, 
				{ 'X', 'X', 'X', 'X', 'X' } };
		Maze maze = new Maze(m1);
		maze.initialize();

		assertEquals(3,maze.dragonGetX());
		assertEquals(3,maze.dragonGetY());
		assertEquals('D', maze.getDragonState());
		assertEquals(State.PLAYING, maze.getState());


		boolean outcome1 = false, outcome2 = false;
		while(!outcome1 && !outcome2)
		{
			maze.updateDragon();
			if(maze.dragonGetX() == 2 && maze.dragonGetY() == 3)
			{
				outcome1 = true;
			}
			else if(maze.dragonGetX() == 3 && maze.dragonGetY() == 2)
			{
				outcome2 = true;
			}
		}
		if(outcome1)
		{
			//System.out.println(i);
			assertEquals(2,maze.dragonGetX());
			assertEquals(3,maze.dragonGetY());
			assertEquals('D', maze.getDragonState());
			assertEquals(State.PLAYING, maze.getState());
			
		}
		else if(outcome2)
		{
			//System.out.println(i);
			assertEquals(3,maze.dragonGetX());
			assertEquals(2,maze.dragonGetY());
			assertEquals('D', maze.getDragonState());
			assertEquals(State.PLAYING, maze.getState());
		}
	}
	
	@org.junit.Test (timeout=1000)
	public void DragonToSword() 
	{
		char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
						{ 'X', ' ', ' ', 'H', 'E' }, 
						{ 'X', ' ', 'X', ' ', 'X' },
						{ 'X', ' ', 'S', 'D', 'X' }, 
						{ 'X', 'X', 'X', 'X', 'X' } };
		Maze maze = new Maze(m1);
		maze.initialize();

		assertEquals(3,maze.dragonGetX());
		assertEquals(3,maze.dragonGetY());
		assertEquals('D', maze.getDragonState());
		assertEquals(State.PLAYING, maze.getState());


		boolean outcome1 = false;
		while(!outcome1)
		{
			maze.updateDragon();
			if(maze.dragonGetX() == 2 && maze.dragonGetY() == 3)
			{
				outcome1 = true;
			}
		}
		if(outcome1)
		{
			assertEquals(2,maze.dragonGetX());
			assertEquals(3,maze.dragonGetY());
			assertEquals('F', maze.getDragonState());
			//System.out.println(i);
		}
	}
	@org.junit.Test (timeout=1000)
	public void DragonToHero() 
	{
		char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
						{ 'X', ' ', ' ', ' ', 'E' }, 
						{ 'X', ' ', 'X', 'H', 'X' },
						{ 'X', 'S', 'X', 'D', 'X' }, 
						{ 'X', 'X', 'X', 'X', 'X' } };
		Maze maze = new Maze(m1);
		maze.initialize();

		assertEquals(3,maze.dragonGetX());
		assertEquals(3,maze.dragonGetY());
		assertEquals('D', maze.getDragonState());
		assertEquals(State.PLAYING, maze.getState());


		boolean outcome1 = false;
		while(!outcome1)
		{
			maze.updateDragon();
			
			if(maze.dragonGetX() == 3 && maze.dragonGetY() == 2)
			{
				outcome1 = true;
			}
		}
		if(outcome1)
		{
			assertEquals(3,maze.dragonGetX());
			assertEquals(2,maze.dragonGetY());
			assertEquals('D', maze.getDragonState());
			assertEquals(State.LOST, maze.getState());
		}
	}
	@org.junit.Test (timeout=1000)
	public void DragonToSleep() 
	{
		char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
						{ 'X', 'H', ' ', ' ', 'E' }, 
						{ 'X', ' ', 'X', ' ', 'X' },
						{ 'X', 'S', 'X', 'D', 'X' }, 
						{ 'X', 'X', 'X', 'X', 'X' } };
		Maze maze = new Maze(m1);
		maze.initialize();

		assertEquals(3,maze.dragonGetX());
		assertEquals(3,maze.dragonGetY());
		assertEquals('D', maze.getDragonState());
		assertEquals(State.PLAYING, maze.getState());


		boolean outcome1 = false;
		while(!outcome1)
		{
			maze.updateDragon();
			
			if(maze.getDragonState() == Table.SLEEPY)
			{
				outcome1 = true;
			}
		}
		if(outcome1)
		{
			assertEquals('d', maze.getDragonState());
		}
	}
}
