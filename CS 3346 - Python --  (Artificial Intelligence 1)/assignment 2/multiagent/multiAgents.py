# multiAgents.py
# --------------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

from util import manhattanDistance
from game import Directions
import random, util

from game import Agent

class ReflexAgent(Agent):
  """
    A reflex agent chooses an action at each choice point by examining
    its alternatives via a state evaluation function.

    The code below is provided as a guide.  You are welcome to change
    it in any way you see fit, so long as you don't touch our method
    headers.
  """


  def getAction(self, gameState):
    """
    You do not need to change this method, but you're welcome to.

    getAction chooses among the best options according to the evaluation function.

    Just like in the previous project, getAction takes a GameState and returns
    some Directions.X for some X in the set {North, South, West, East, Stop}
    """
    # Collect legal moves and successor states
    legalMoves = gameState.getLegalActions()

    # Choose one of the best actions
    scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
    bestScore = max(scores)
    bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
    chosenIndex = random.choice(bestIndices) # Pick randomly among the best

    "Add more of your code here if you want to"

    return legalMoves[chosenIndex]

  def evaluationFunction(self, currentGameState, action):
    """
    Design a better evaluation function here.

    The evaluation function takes in the current and proposed successor
    GameStates (pacman.py) and returns a number, where higher numbers are better.

    The code below extracts some useful information from the state, like the
    remaining food (oldFood) and Pacman position after moving (newPos).
    newScaredTimes holds the number of moves that each ghost will remain
    scared because of Pacman having eaten a power pellet.

    Print out these variables to see what you're getting, then combine them
    to create a masterful evaluation function.
    """
    # Useful information you can extract from a GameState (pacman.py)
    successorGameState = currentGameState.generatePacmanSuccessor(action)
    newPos = successorGameState.getPacmanPosition()
    oldFood = currentGameState.getFood()
    newGhostStates = successorGameState.getGhostStates()
    newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

    "*** YOUR CODE HERE ***"
    #A capable reflex agent will have to consider both food locations and ghost locations to perform well
    
    #the food states are: successorGameState.getFood().asList()
    newFood = currentGameState.generatePacmanSuccessor(action).getFood().asList()	#new food
    output = 0	

    fMin = 99999			#initialize the min for food
    for food in newFood:	#for the food states (go through the list)
        u = util.manhattanDistance(newPos, food) #food heuristic
        if u < fMin and u != 0:#update
            fMin = u


    gMin = 99999
    for ghostState in newGhostStates:		#get the ghost states
    	#heuristic for the ghost position
        g = util.manhattanDistance(newPos, ghostState.getPosition())	
        if g < gMin:	#if it is less than the ghost min, set it to be equal
            gMin = g    #set gMin to be the heuristic
            
    if gMin == 0 or gMin > 20:		#if ghost min
        gMin = -1000		#the new ghostmin is -1000
        
    return gMin/fMin + successorGameState.getScore()	#return the mins and the score

    return successorGameState.getScore()		#return the score

def scoreEvaluationFunction(currentGameState):
  """
    This default evaluation function just returns the score of the state.
    The score is the same one displayed in the Pacman GUI.

    This evaluation function is meant for use with adversarial search agents
    (not reflex agents).
  """
  return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
  """
    This class provides some common elements to all of your
    multi-agent searchers.  Any methods defined here will be available
    to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

    You *do not* need to make any changes here, but you can if you want to
    add functionality to all your adversarial search agents.  Please do not
    remove anything, however.

    Note: this is an abstract class: one that should not be instantiated.  It's
    only partially specified, and designed to be extended.  Agent (game.py)
    is another abstract class.
  """

  def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
    self.index = 0 # Pacman is always agent index 0
    self.evaluationFunction = util.lookup(evalFn, globals())
    self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
  """
    Your minimax agent (question 2)
  """

  def getAction(self, gameState):
    """
      Returns the minimax action from the current gameState using self.depth
      and self.evaluationFunction.

      Here are some method calls that might be useful when implementing minimax.

      gameState.getLegalActions(agentIndex):
        Returns a list of legal actions for an agent
        agentIndex=0 means Pacman, ghosts are >= 1

      Directions.STOP:
        The stop direction, which is always legal

      gameState.generateSuccessor(agentIndex, action):
        Returns the successor game state after an agent takes an action

      gameState.getNumAgents():
        Returns the total number of agents in the game
    """
    "*** YOUR CODE HERE ***"
    return self.miniMaxDecision(gameState)#calls the function on self. sends the gamestate

 	#should work with one ghost
    #score the leaves of the minimax tree with supplied self.evaluationFunction, which degaults to scoreEvaluationFunction
    #this MinimaxAgents class extends MultiAgent, which gives access to self.depth and self.evaluationFunction
    #minimax code should reference the above two variables when appropriate

    #a single search ply is considered to be one Pacman move and the ghost's response
    #so a depth 2 search will involve pacman and the ghost moving two times


    #MINIMAX ALGOIRTHM
    #function MINIMAX-DECISION(state) returns an action
    #return arg max_actions(s) min-value(result(state,a))
    #
    #function max_value(state) returns a utility value
    #if terminal-tests(state) then return utility(state)
    #v = -infinity 
    #for each a in actions(state) do
    #v = max(v, min-value(results(s,a))) return v
    #
    #function min-value(state) returns a utility value
    #if terminal-tests(state) then return utlity(state)
    #v = infinity
    #for each a in action(state) do
    #v = min(v, max-value(result(s,a)))
    #return v

  def miniMaxDecision(self, gameState):
      #best action variable. this will be the best action to take
      bestAction = ""
      #current value
      v = -100000#initlize it to be negative infinity
      
	#go through all legal actions so we can find the best action to take
      for action in gameState.getLegalActions():
      	  #initialize a variable to be equal to the current value v. this will allow us to compare
          prev = v
          #get the new value (the max of the min)
          v = max(v, self.findMin(gameState.generateSuccessor(0, action), 0, 1))

          #if the current value is better than the previous
          if v > prev:
          	  #update the best action
              bestAction = action

      return bestAction #return best action


  #function for maximizing
  def findMax(self, gameState, depth):
        
      v = -100000 #set current value equal to negative infinity

      if gameState.isWin() or gameState.isLose() or depth == self.depth - 1: #bottom has been reached, evaluate
          return self.evaluationFunction(gameState)
      
      #Returns value for each action  
      for action in gameState.getLegalActions(0):
          if action != Directions.STOP:
              v = max(v, self.findMin(gameState.generateSuccessor(0, action), depth, 1))

      return v


  #function for minimizing 
  def findMin(self, gameState, depth, numGhost):

      if gameState.isWin() or gameState.isLose() or depth == self.depth - 1: #bottom has been reached, evaluate
          return self.evaluationFunction(gameState)
      
      v = 100000#sys.maxint

      #Run through all ghosts and get their mins, once last ghost is reached and depth hasn't been achieved, max is called  
      for action in gameState.getLegalActions(numGhost):
          if action != Directions.STOP:
              if numGhost == gameState.getNumAgents() - 1:
                  v = min(v, self.findMax(gameState.generateSuccessor(numGhost, action), depth + 1))
              else:
                  v = min(v, self.findMin(gameState.generateSuccessor(numGhost, action), depth, numGhost + 1))
      return v

    #util.raiseNotDefined()


class AlphaBetaAgent(MultiAgentSearchAgent):
  """
    Your minimax agent with alpha-beta pruning (question 3)
  """

  def getAction(self, gameState):
    """
      Returns the minimax action using self.depth and self.evaluationFunction
    """
    "*** YOUR CODE HERE ***"
    #minimax values in this class should be identical to MinimaxAgent's minimax values, but actions it selects can vary cus of tie breaking behavior
    #util.raiseNotDefined()

    #min and max functions similar to the minimax agent, except we are passinf alpha and beta values

    #for maximizing
    def findMax(state, alpha, beta, currentDepth):
      currentDepth = currentDepth + 1#this is just for comparing below. it's same as depth == self.depth - 1, but for some odd reason it was giving me errors
      if state.isWin() or state.isLose() or currentDepth == self.depth: #bottom has been reached, evaluate
        return self.evaluationFunction(state)
      v = -100000#initialize current value to be alpha (negative inifnity)
      #go through the actions
      for pAction in state.getLegalActions(0):
        v = max(v, findMin(state.generateSuccessor(0, pAction), alpha, beta, currentDepth, 1))#get the max
        if v >= beta:#and compare with beta
          return v#prune
        alpha = max(alpha, v)#otherwise update alpha if needed and continue
      return v

    #minimizing
    def findMin(state, alpha, beta, currentDepth, ghostNum):
      if state.isWin() or state.isLose():
        return self.evaluationFunction(state)
      v = 100000#initialize current value to be beta (infinity)
      #go through actions
      for pAction in state.getLegalActions(ghostNum):
        if ghostNum == gameState.getNumAgents() - 1:
          v = min(v, findMax(state.generateSuccessor(ghostNum, pAction), alpha, beta, currentDepth))
        else:
          v = min(v, findMin(state.generateSuccessor(ghostNum, pAction), alpha, beta, currentDepth, ghostNum + 1))
        if v <= alpha:
          return v
        beta = min(beta, v)#update beta if needed
      return v
      
    # this is the alpha beta process
    #initialize variables
    v = -100000#to start, current value is alpha(neg inf)
    alpha = -100000
    beta = 100000#positive inf
    bestAction = ''#best action is null
    #go though possible actions
    for action in gameState.getLegalActions(0):
      currentDepth = 0
      currv = findMin(gameState.generateSuccessor(0, action), alpha, beta, currentDepth, 1)#to compare the values
      if currv > v:#we are maximizing, so if it's greater, update the value
        v = currv
        bestAction = action#and update the best action
      if currv > beta:
      	return bestAction
      alpha = max(alpha, currv)#update alpha
    return bestAction


class ExpectimaxAgent(MultiAgentSearchAgent):
  """
    Your expectimax agent (question 4)
  """

  def getAction(self, gameState):
    """
      Returns the expectimax action using self.depth and self.evaluationFunction

      All ghosts should be modeled as choosing uniformly at random from their
      legal moves.
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

def betterEvaluationFunction(currentGameState):
  """
    Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
    evaluation function (question 5).

    DESCRIPTION: <write something here so we know what you did>
  """
  "*** YOUR CODE HERE ***"
  util.raiseNotDefined()

# Abbreviation
better = betterEvaluationFunction

class ContestAgent(MultiAgentSearchAgent):
  """
    Your agent for the mini-contest
  """

  def getAction(self, gameState):
    """
      Returns an action.  You can use any method you want and search to any depth you want.
      Just remember that the mini-contest is timed, so you have to trade off speed and computation.

      Ghosts don't behave randomly anymore, but they aren't perfect either -- they'll usually
      just make a beeline straight towards Pacman (or away from him if they're scared!)
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

