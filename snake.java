///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS io.quarkus:quarkus-bom:${quarkus.version:2.6.2.Final}@pom
////DEPS io.quarkus:quarkus-resteasy-reactive
//DEPS io.quarkus:quarkus-resteasy-reactive-jackson
//JAVAC_OPTIONS -parameters
//JAVA 17

import com.fasterxml.jackson.databind.JsonNode;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

enum Moves {
    up, down, left, right
}

@Path("/")
@ApplicationScoped
public class snake {

    @Inject
    Logger log;

    /**
     * This function is called when you register your Battlesnake on play.battlesnake.com
     * See https://docs.battlesnake.com/guides/getting-started#step-4-register-your-battlesnake
     * <p>
     * It controls your Battlesnake appearance and author permissions.
     * For customization options, see https://docs.battlesnake.com/references/personalization
     * <p>
     * TIP: If you open your Battlesnake URL in browser you should see this data.
     *
     * @return Info about your snake
     */
    @GET
    public Info info() {
        log.infof("INFO");
        return new Info("1",
                "",
                "#888888",
                "default",
                "default");
    }

    /**
     * This command is called once at the beginning of every game to let your
     * Battlesnake know that a new game is about to start.
     *
     * @param state contains info about the boards initial state and rules
     */
    @POST
    @Path("/start")
    public void start(GameState state) {
        log.infof("%s START", state.game().id());
    }

    /**
     * This command is called once per turn of each game,
     * providing information about the game board to your
     * Battlesnake and asking for its next move.
     *
     * Your response to this command determines how your
     * Battlesnake behaves and will be the primary focus
     * of your game logic programming.
     *
     * @param state contains info about the boards current state and rules
     * @return a Move response back for what move you wish to do: up, down, left or right.
     */
    @POST
    @Path("/move")
    public Move move(GameState state) {
        log.infof("move called %s", state);

        var head = state.you().head();
        var body = state.you().body();

        var possibleMoves = new ArrayList<String>(Arrays.asList("up","down","left","right"));

        // Don't allow your Battlesnake to move back in on it's own neck
        avoidMyNeck(head, body, possibleMoves);

        // TODO: Using information from 'state', find the edges of the board and
        // don't
        // let your Battlesnake move beyond them board_height = ? board_width = ?

        // TODO Using information from 'state', don't let your Battlesnake pick a
        // move that would hit its own body

        // TODO: Using information from 'state', don't let your Battlesnake pick a
        // move that would collide with another Battlesnake

        // TODO: Using information from 'state', make your Battlesnake move
        // towards a piece of food on the board

        var choice = new Random().nextInt(possibleMoves.size());
        Move move = new Move(possibleMoves.get(choice));

        log.infof("MOVE %s", move);

        return move;
    }

    /**
     * This command is called once after each game has been
     * completed to let your Battlesnake know that the game is over.
     *
     * @param state contains info about the boards end state and rules
     */
    @POST
    @Path("end")
    public void end(GameState state) {
        log.infof("%s END", state.game().id());
    }

    /**
     * Remove the 'neck' direction from the list of possible moves
     *
     * @param head          JsonNode of the head position e.g. {"x": 0, "y": 0}
     * @param body          JsonNode of x/y coordinates for every segment of a
     *                      Battlesnake. e.g. [ {"x": 0, "y": 0}, {"x": 1, "y": 0},
     *                      {"x": 2, "y": 0} ]
     * @param possibleMoves ArrayList of String. Moves to pick from.
     */
    public void avoidMyNeck(Coord head, Coord[] body, List<String> possibleMoves) {
        var neck = body[0];

        if (neck.x() < head.x()) {
            possibleMoves.remove("left");
        } else if (neck.x() > head.x()) {
            possibleMoves.remove("right");
        } else if (neck.y() < head.y()) {
            possibleMoves.remove("down");
        } else if (neck.y() > head.y()) {
            possibleMoves.remove("up");
        }
    }
}

record Info(String apiversion,
            String author,
            String color,
            String head,
            String tail) {
    public Info() {
        this("1",
                "",
                "#888888",
                "default",
                "default");
    }
}

record Move(
        String move,
        String shout
) {
    public Move(String move) { this(move, null);}
}

record GameState(
        Game game,
        int turn,
        Board board,
        Battlesnake you
) {
}

record Game(String id,
            Ruleset ruleset,
            long timeout) {
}

record Ruleset(String name,
               String version,
               Settings settings) {
}

record Settings(
        long foodSpawnChance,
        long minimumFood,
        long hazardDamagePerTurn,
        Royale royale,
        Squad squad
) {
}

record Royale(long shrinkEveryNTurns) {
}

record Squad(boolean allowBodyCollisions,
             boolean sharedElimination,
             boolean sharedHealth,
             boolean sharedLength) {
}

record Board(int height,
             int width,
             Coord[] food,
             Battlesnake[] snakes,
             Coord[] hazards) {
}

record Battlesnake(String id,
                   String name,
                   long health,
                   Coord[] body,
                   Coord head,
                   long length,
                   String latency,
                   String shout,
                   String squad) {
}

record Coord(int x, int y) {
}


