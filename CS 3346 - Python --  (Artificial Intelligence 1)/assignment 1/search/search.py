# search.py
# ---------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

"""
In search.py, you will implement generic search algorithms which are called 
by Pacman agents (in searchAgents.py).
"""

#edited by Vivian Lam. CS3346, 2016

import util

class SearchProblem:
  """
  This class outlines the structure of a search problem, but doesn't implement
  any of the methods (in object-oriented terminology: an abstract class).
  
  You do not need to change anything in this class, ever.
  """
  
  def getStartState(self):
     """
     Returns the start state for the search problem 
     """
     util.raiseNotDefined()
    
  def isGoalState(self, state):
     """
       state: Search state
    
     Returns True if and only if the state is a valid goal state
     """
     util.raiseNotDefined()

  def getSuccessors(self, state):
     """
       state: Search state
     
     For a given state, this should return a list of triples, 
     (successor, action, stepCost), where 'successor' is a 
     successor to the current state, 'action' is the action
     required to get there, and 'stepCost' is the incremental 
     cost of expanding to that successor
     """
     util.raiseNotDefined()

  def getCostOfActions(self, actions):
     """
      actions: A list of actions to take
 
     This method returns the total cost of a particular sequence of actions.  The sequence must
     be composed of legal moves
     """
     util.raiseNotDefined()
           

def tinyMazeSearch(problem):
  """
  Returns a sequence of moves that solves tinyMaze.  For any other
  maze, the sequence of moves will be incorrect, so only use this for tinyMaze
  """
  from game import Directions
  s = Directions.SOUTH
  w = Directions.WEST
  return  [s,s,w,s,w,w,s,w]

def depthFirstSearch(problem):
  """
  Search the deepest nodes in the search tree first [p 85].
  
  Your search algorithm needs to return a list of actions that reaches
  the goal.  Make sure to implement a graph search algorithm [Fig. 3.7].
  
  To get started, you might want to try some of these simple commands to
  understand the search problem that is being passed in:
  
  print "Start:", problem.getStartState()
  print "Is the start a goal?", problem.isGoalState(problem.getStartState())
  print "Start's successors:", problem.getSuccessors(problem.getStartState())
  """
  "*** YOUR CODE HERE ***"
  print "Start:", problem.getStartState()
  print "Is the start a goal?", problem.isGoalState(problem.getStartState())
  print "Start's successors:", problem.getSuccessors(problem.getStartState())
  
  #USING TEXTBOOK ALGORITHM
  #util.raiseNotDefined()
  frontier = util.Stack()                                 #DFS uses the stack
  #frontier.push([(problem.getStartState(), "Stop" , 0)])  #initialize frontier using initial state of problem
                                                          #start state, no direction, 0 cost cus root
  frontier.push( (problem.getStartState(), [], []) )
  explored = []                                           #initialize expored set to be empty
  while frontier:                           #if not empty
    #choose fringe node and remove from fringe
    node, actions, visited = frontier.pop()
    #if node contains a goal state returtn solution(path from initial node ot goal node)
    if problem.isGoalState(node):
      return actions + [direction]
    #(add node to explored set) expand chosen node by adding the resulting nodes to the fringe
    #(only if not in fringe or explored set)
    for childState, direction, steps in problem.getSuccessors(node):
      if not childState in visited:
        if problem.isGoalState(childState):
          return actions + [direction]
        frontier.push((childState, actions + [direction], visited + [node] ))
  return[] #if frontier is empty return failure
      
def breadthFirstSearch(problem):
  "Search the shallowest nodes in the search tree first. [p 81]"
  "*** YOUR CODE HERE ***"
  #CODE MADE TO BE MORE GENERAL GRAPH SEARCH
  #util.raiseNotDefined()
  frontier = util.PriorityQueueWithFunction(len)			#use priority queue	
  frontier.push([(problem.getStartState(), "Stop" , 0)])  #initialize frontier using initial state of problem
                                                          #start state, no direction, 0 cost cus root
  explored = []                                           #initialize expored set to be empty
  while frontier:                           #if not empty
    #choose fringe node and remove from fringe
    path = frontier.pop()
    node = path[len(path)-1]
    node = node[0]
    
    #if node contains a goal state returtn solution(path from initial node ot goal node)
    if problem.isGoalState(node):
      return [x[1] for x in path][1:]
    #(add node to explored set) expand chosen node by adding the resulting nodes to the fringe
    #(only if not in fringe or explored set)
    if node not in explored:
      explored.append(node)
      nodeSucc = problem.getSuccessors(node)
      for succ in nodeSucc:
	if succ[0] not in explored:
  	  succPath = path[:]
  	  succPath.append(succ)
  	  frontier.push(succPath)
  return[] #if frontier is empty return failure
      
def uniformCostSearch(problem):
  "Search the node of least total cost first. "
  "*** YOUR CODE HERE ***"
  #util.raiseNotDefined()
  cost = lambda aPath: problem.getCostOfActions([x[1] for x in aPath])
  frontier = util.PriorityQueueWithFunction(cost)
  frontier.push([(problem.getStartState(), "Stop" , 0)])  #initialize frontier using initial state of problem
                                                          #start state, no direction, 0 cost cus root
  explored = []                                           #initialize expored set to be empty
  while frontier:                           #if not empty
    #choose fringe node and remove from fringe
    path = frontier.pop()
    node = path[len(path)-1]
    node = node[0]
    
    #if node contains a goal state returtn solution(path from initial node ot goal node)
    if problem.isGoalState(node):
      return [x[1] for x in path][1:]
    #(add node to explored set) expand chosen node by adding the resulting nodes to the fringe
    #(only if not in fringe or explored set)
    if node not in explored:
      explored.append(node)
      nodeSucc = problem.getSuccessors(node)
      for succ in nodeSucc:
	if succ[0] not in explored:
  	  succPath = path[:]
  	  succPath.append(succ)
  	  frontier.push(succPath)
  return[] #if frontier is empty return failure


def nullHeuristic(state, problem=None):
  """
  A heuristic function estimates the cost from the current state to the nearest
  goal in the provided SearchProblem.  This heuristic is trivial.
  """
  return 0

def aStarSearch(problem, heuristic=nullHeuristic):
  "Search the node that has the lowest combined cost and heuristic first."
  "*** YOUR CODE HERE ***"
  #USING MIX OF ALGORITHM FROM TEXTBOOK AND ONLINE
  cost = lambda aPath: problem.getCostOfActions([x[1] for x in aPath]) + heuristic(aPath[len(aPath)-1][0], problem)
  frontier = util.PriorityQueueWithFunction(cost)
  frontier.push([(problem.getStartState(), "Stop" , 0)])  #initialize frontier using initial state of problem
                                                          #start state, no direction, 0 cost cus root
  explored = []                                           #initialize expored set to be empty
  while frontier:                           #if not empty
    #choose fringe node and remove from fringe
    path = frontier.pop()
    node = path[len(path)-1]
    node = node[0]
    
    #if node contains a goal state returtn solution(path from initial node ot goal node)
    if problem.isGoalState(node):
      return [x[1] for x in path][1:]
    #(add node to explored set) expand chosen node by adding the resulting nodes to the fringe
    #(only if not in fringe or explored set)
    if node not in explored:
      explored.append(node)
      nodeSucc = problem.getSuccessors(node)
      for succ in nodeSucc:
	if succ[0] not in explored:
  	  succPath = path[:]
  	  succPath.append(succ)
  	  frontier.push(succPath)
  return[] #if frontier is empty return failure


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
