package SnakeAndLadderService;

import SnakeAndLadderService.Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SnakeAndLadder {
    private Map<Integer, User> userMap;
    private Map<Integer, Integer> snakesMap;
    private Map<Integer, Integer> laddersMap;
    private Random random;

    public SnakeAndLadder(Map<Integer, User> users, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        this.userMap = users;
        this.snakesMap = snakes;
        this.laddersMap = ladders;
        this.random = new Random();
    }

    public void playGame() {
        int totalPlayers = userMap.size();
        int playerNumber = 1;
        while(true) {
            User user = userMap.get(playerNumber);
            int diceValue = random.nextInt(6) + 1;
            int oldPosition = user.getPosition();
            int newPosition = oldPosition + diceValue;
            Integer endSnakePosition = checkAndGetPositionOfSnake(newPosition);
            if(endSnakePosition != null) {
                Integer endLadderPosition = checkAndGetPositionOfLadder(endSnakePosition);
                if(endLadderPosition != null)
                    newPosition = endLadderPosition;
                else
                    newPosition = endSnakePosition;
            } else {
                Integer endLadderPosition = checkAndGetPositionOfLadder(newPosition);
                if(endLadderPosition != null) {
                    Integer newEndSnakePosition = checkAndGetPositionOfSnake(endLadderPosition);
                    if(newEndSnakePosition != null) {
                        newPosition = newEndSnakePosition;
                    } else {
                        newPosition = endLadderPosition;
                    }
                }
            }
            System.out.println(user.getUserName() +" rolled a "+diceValue+" and moved from "+oldPosition +" to "+newPosition);
            if(newPosition == 36) {
                user.setPosition(newPosition);
                System.out.println(user.getUserName()+" wins the game");
                break;
            } else if(newPosition < 100) {
                user.setPosition(newPosition);
            }
            if(totalPlayers == playerNumber) {
                playerNumber = 1;
            } else {
                playerNumber++;
            }

        }
    }

    public Integer checkAndGetPositionOfSnake(int position) {
        return snakesMap.get(position);
    }

    public Integer checkAndGetPositionOfLadder(int position) {
        return laddersMap.get(position);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nofOfSnakes = Integer.parseInt(br.readLine());
        Map<Integer, Integer> snakes = new HashMap<>();
        while(nofOfSnakes --> 0) {
            String[] positions = br.readLine().split(" ");
            snakes.put(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
        }
        int noOfLadders = Integer.parseInt(br.readLine());
        Map<Integer, Integer> ladders = new HashMap<>();
        while(noOfLadders --> 0) {
            String[] positions = br.readLine().split(" ");
            ladders.put(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
        }

        int noOfPlayers = Integer.parseInt(br.readLine());
        Map<Integer, User> users = new HashMap<>();
        int playerPosition = 1;
        while(noOfPlayers --> 0) {
            String userName = br.readLine();
            User user = new User(userName, 0);
            users.put(playerPosition++, user);
        }
        SnakeAndLadder snakeAndLadder = new SnakeAndLadder(users, snakes, ladders);
        snakeAndLadder.playGame();
    }
}
