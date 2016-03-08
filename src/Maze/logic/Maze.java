package Maze.logic;

import java.util.Random;

public class Maze 
{
	public enum State { PLAYING, WON, LOST, SLAYED };

	private State state;

	Table table;								//Object Table
	Hero hero;									//Object Hero
	Dragon dragon;								//Object Dragon~
	Sword sword;								//Object Sword
	Random r;
	
	public Maze(){};
		
	public Maze(char[][] newMaze)
	{
		table.assertNewTable(newMaze);
		assertNewElem();
	}
	
	public void Initialize()
	{
		table = new Table();
		hero = new Hero();
		dragon = new Dragon();
		sword = new Sword();
		r = new Random();

		setState(State.PLAYING);				//Setting Game to play
		hero.HeroDeploy(table);					//Deploy Hero in table
		dragon.DragonDeploy(table);				//Deploy Dragon in table
		sword.SwordDeploy(table);				//Deploy Sword in table
		Print();								//Printing Table
	}

	public void setState(State status)
	{
		state = status;
	}

	public State getState()
	{
		return state;
	}
	public void Print()
	{
		table.PrintTable();
	}

	public boolean UpdateHero(String c)
	{
		if(c == "Up")
		{
			int newPosX =  0 + hero.getX();
			int newPosY = -1 + hero.getY();

			if(HeroMove(newPosX, newPosY) == true)
			{
				hero.setCoord(newPosX, newPosY);
				System.out.println("Up");
				return true;
			}
			return false;
		}
		if(c == "Left")
		{
			int newPosX =  -1 + hero.getX();
			int newPosY = 	0 + hero.getY();

			if(HeroMove(newPosX, newPosY) == true)
			{
				hero.setCoord(newPosX, newPosY);
				System.out.println("Left");
				return true;
			}
			return false;


		}
		if(c == "Down")
		{
			int newPosX =   0 + hero.getX();
			int newPosY =  +1 + hero.getY();

			if(HeroMove(newPosX, newPosY) == true)
			{
				hero.setCoord(newPosX, newPosY);
				System.out.println("Down");
				return true;
			}
			return false;

		}
		if(c == "Right")
		{
			int newPosX =  +1 + hero.getX();
			int newPosY = 	0 + hero.getY();

			if(HeroMove(newPosX, newPosY) == true)
			{
				hero.setCoord(newPosX, newPosY);
				System.out.println("Right");
				return true;
			}
			return false;

		}
		return false;
	}
	public boolean HeroMove(int nPosX, int nPosY)
	{
		char Elem = table.getElem(nPosX, nPosY);
		char Hero = hero.getHeroState();

		//HERO GATHERING THE SWORD
		if(Elem == SWORD())
		{
			table.setElem(hero.getX(), hero.getY(), SPACE());
			table.setElem(nPosX, nPosY, hero.heroArmed());
			return true;
		}

		//CASE WHEN HERO UNARMED CONFRONTS DRAGON, DIES HORRIBLY
		else if(Elem == DRAGON() && Hero == HERO())
		{
			table.setElem(hero.getX(), hero.getY(), SPACE());
			setState(State.LOST);
			return true;
		}

		//CASE WHEN HERO IS ARMED AND CONFRONTS SLEEPY OR AWAKEN DRAGON, DRAGON GETS CRIPPLED INT PIECES
		else if((Elem == DRAGON() && Hero == ARMOR())||
				(Elem == SLEEPY() && Hero == ARMOR()))
		{
			table.setElem(HeroGetX(), hero.getY(), SPACE());
			table.setElem(nPosX, nPosY, Hero);
			setState(State.SLAYED);
			return true;
		}

		//SLEEPY DRGON AND HERO UNAMRMED
		else if(Elem == SLEEPY() && Hero == HERO())
		{
			//SIMPLY RETURNS FALSE FOR THE HERO CANNOT MAGICLLY TRESPASS THE DRAGON
			return false;
		}
		//HERO FACES DRAGON SITTING ON SWORD
		else if((Elem == DRASWO() && Hero == HERO())||
				(Elem == DRASWO() && Hero == ARMOR()))
		{
			return false;
		}
		//KILLING DRAGON AND ENTERING THE EXIT
		else if(Elem == EXIT() && getState() == State.SLAYED)
		{
			table.setElem(hero.getX(), hero.getY(), SPACE());
			table.setElem(nPosX, nPosY, Hero);
			setState(State.WON);
			return true;
		}
		//NORMAL HERO MOVEMENT
		else if(Elem == SPACE())
		{
			table.setElem(hero.getX(), hero.getY(), SPACE());
			table.setElem(nPosX, nPosY, Hero);
			return true;
		}
		else
			return false;
	}

	public boolean UpdateDragon()
	{
		int move;
		int newPosX = dragon.getX();
		int newPosY = dragon.getY();
		char Dragon = dragon.getDragonState();

		move = r.nextInt(2);

		//MOVIMENTO
		if(move == 0 && Dragon != SLEEPY())
		{
			//MOVIMENTO OBRIGATORIO
			while(true)
			{	
				move = r.nextInt(4);
				//UPPER MOVEMENT
				if(move == 0)
				{
					int change = -1;
					if(DragonMove(newPosX, newPosY + change) == true)
						return true;
				}
				//DOWN MOVEMENT
				else if(move == 1)
				{
					int change = +1;
					if(DragonMove(newPosX, newPosY + change) == true)
						return true;
				}
				//LEFT MOVEMENT
				else if(move == 2)
				{
					int change = -1;
					if(DragonMove(newPosX+change, newPosY) == true)
						return true;
				}
				//RIGHT MOVEMENT
				else if(move == 3)
				{
					int change = +1;
					if(DragonMove(newPosX+change, newPosY) == true)
						return true;
				}
				else return false;
			}
		}
		else if(move == 0 && Dragon == SLEEPY())
		{
			move = r.nextInt(2);
			if(move == 0)
			{
				return false;
			}
			else if(move == 1)
			{
				if(Dragon == DRAGON())
				{
					dragon.sleepDragon();
					table.setElem(newPosX, newPosY, SLEEPY());
					return false;
				}
				else if(Dragon == SLEEPY())
				{
					dragon.awakeDragon();
					table.setElem(newPosX, newPosY, DRAGON());
					return false;
				}
				else
					return false;
			}
		}
		else if(move == 1 && Dragon != DRASWO())
		{
			move = r.nextInt(2);
			if(move == 0)
			{
				return false;
			}
			else if(move == 1)
			{
				if(Dragon == DRAGON())
				{
					dragon.sleepDragon();
					table.setElem(newPosX, newPosY, SLEEPY());
					System.out.println("Dragon is Sleeping!");
					return false;
				}
				else if(Dragon == SLEEPY())
				{
					dragon.awakeDragon();
					table.setElem(newPosX, newPosY, DRAGON());
					System.out.println("DRAGON AS AWAKEN!");
					return false;
				}
				else
					return false;
			}
			else 
				return false;
		}
		return false;
	}
	public boolean DragonMove(int newPosX, int newPosY)
	{
		char Elem = table.getElem(newPosX, newPosY);
		char Dragon = dragon.getDragonState();
		//FOR NORMAL DRAGON AND SPACE MOVEMENT
		if(Elem == SPACE() && Dragon == DRAGON())
		{
			table.setElem(dragon.getX(), dragon.getY(),SPACE());
			table.setElem(newPosX, newPosY, DRAGON());
			dragon.setCoord(newPosX, newPosY);
			return true;
		}
		//FOR DRAGON TO SWORD MOVEMENT
		else if(Elem == SWORD())
		{
			table.setElem(dragon.getX(), dragon.getY(),SPACE());
			table.setElem(newPosX, newPosY, DRASWO());
			dragon.setCoord(newPosX, newPosY);
			dragon.swordDragon();
			return true;
		}
		//GETTING OUT OF THE SWORD
		else if(Elem == SPACE() && Dragon == DRASWO())
		{
			table.setElem(dragon.getX(), dragon.getY(),SWORD());
			table.setElem(newPosX, newPosY, DRAGON());
			dragon.setCoord(newPosX, newPosY);
			dragon.noSwordDragon();
			return true;
		}
		//DRAGON KILLING HERO, ARMED OR UNARMED
		else if(Elem == HERO() && Dragon == DRAGON() || Elem == ARMOR() && Dragon == DRAGON())
		{
			table.setElem(dragon.getX(), dragon.getY(),SPACE());
			table.setElem(newPosX, newPosY, DRAGON());
			dragon.setCoord(newPosX, newPosY);
			setState(State.LOST);
			return true;
		}
		//DRAGON KILLING HERO AND GETTING OUT OF SWORD
		else if(Elem == HERO() && Dragon == DRASWO())
		{
			table.setElem(dragon.getX(), dragon.getY(),SWORD());
			table.setElem(newPosX, newPosY, DRAGON());
			dragon.setCoord(newPosX, newPosY);
			setState(State.LOST);
			return true;
		}
		return false;
	}
	public void assertNewElem()
	{
		int Column = Column();
		int Line = Line();
		
		for(int i = 0; i < Line; i++)
		{
			for(int j = 0; j < Column; j++)
			{
				if(table.getElem(i,j) == 'S')
					sword.setCoord(i, j);
				else if(table.getElem(i, j) == 'H')
					hero.setCoord(i, j);
				else if(table.getElem(i,j) == 'D')
					dragon.setCoord(i, j);
			}
		}
	}

	//MUTATIONAL FUNCTIONS
	public int Column()
	{
		return table.Column();
	}
	public int Line()
	{
		return table.Line();
	}
	public boolean HeroMoveLeft()
	{
		return UpdateHero("Left");
	}
	public boolean HeroMoveRight()
	{
		return UpdateHero("Right");
	}
	public boolean HeroMoveUp()
	{
		return UpdateHero("Up");
	}
	public boolean HeroMoveDown()
	{
		return UpdateHero("Down");
	}
	public int HeroGetX()
	{
		return hero.getX();
	}
	public int HeroGetY()
	{
		return hero.getY();
	}
	public int DragonGetX()
	{
		return dragon.getX();
	}
	public int DragonGetY()
	{
		return dragon.getY();
	}
	public char SPACE()
	{
		return table.SPACE();
	}
	public char SLEEPY()
	{
		return table.SLEEPY();
	}
	public char HERO()
	{
		return table.HERO();
	}
	public char DRAGON()
	{
		return table.DRAGON();
	}
	public char EXIT()
	{
		return table.EXIT();
	}
	public char SWORD()
	{
		return table.SWORD();
	}
	public char ARMOR()
	{
		return table.ARMOR();
	}
	public char DRASWO()
	{
		return table.DRASWO();
	}
}