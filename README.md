# A simple [Battlesnake](http://play.battlesnake.com) written in Java using JBang and Quarkus.

This is a basic implementation of the [Battlesnake API](https://docs.battlesnake.com/snake-api). It's a great starting point for anyone wanting to program their first Battlesnake using Java. It comes ready to use with [Replit.com](https://replit.com) and provides instructions below for getting started. It can also be deployed to [Heroku](https://heroku.com), or any other cloud provider you'd like.

### Technologies

* [Java 17](https://adoptium.net/)
* [JBang](https://jbang.dev/downloads)
* [Quarkus](https://quarkus.io)
* [Heroku](https://www.heroku.com/) (optional)

## Quickstart

The [Quick Start Coding Guide](https://docs.battlesnake.com/guides/getting-started) provides the full set of instructions to customize, register, and create your first games with your Battlesnake! While the guide uses [Repl.it](https://repl.it) as an example host, the instructions can be modified to work with any hosting provider. You can also find advice on other hosting providers on our [Hosting Suggestions](https://docs.battlesnake.com/references/hosting-suggestions) page.

### Prerequisites

* A free [Battlesnake Account](https://play.battlesnake.com/?utm_source=github&utm_medium=readme&utm_campaign=python_starter&utm_content=homepage)

---

## Running Your Battlesnake on [replit.com](https://replit.com)

[![Run on Replit.com](https://replit.com/badge/github/jbangdev/jbang-battlesnake)](https://replit.com/github/jbangdev/jbang-battlesnake)

1. Login to your [Replit.com](https://replit.com) account.

2. Click the 'Run on replit.com' button above, or visit the following URL: https://replit.com/github/jbangdev/jbang-battlesnake.

3. You should see your Repl being initialized - this might take a few moments to complete.

4. Once your Repl is ready to run, click `Run ▶️` at the top of the screen. You should see jbang (and any other dependencies) being installed. Once installation is complete, your Battlesnake server will start and you should see the following:

    ```
    [Thread-0] INFO org.eclipse.jetty.server.Server - Started @ms
    ```

5. Above the terminal window you'll see the live output from your Battlesnake server, including its URL. That URL will be the URL used to create your Battlesnake in the next step. If you visit that URL in your browser, you should see text similar to this:

    ```
    {"apiversion": "1", "author": "", "color": "#888888", "head": "default", "tail": "default"}
    ```

This means your Battlesnake is running correctly on Replit.com.

**At this point your Battlesnake is live and ready to enter games!**



## Customizing Your Battlesnake

Locate the `index` method inside [snake.java](snake.java#L44). You should see code that looks like this:
```java
return new Info("1",
                "",
                "#888888",
                "default",
                "default");
```

This function is called by the game engine periodically to make sure your Battlesnake is healthy, responding correctly, and to determine how your Battlesnake will appear on the game board. See [Battlesnake Personalization](https://docs.battlesnake.com/references/personalization) for how to customize your Battlesnake's appearance using these values.

Whenever you update these values, you can refresh your Battlesnake on [your profile page](https://play.battlesnake.com/me/) to use your latest configuration. Your changes should be reflected in the UI, as well as any new games created.

## Changing Behavior

On every turn of each game your Battlesnake receives information about the game board and must decide its next move.

Locate the `move` method inside [Snake.java](snake.java#L79).

Possible moves are "up", "down", "left", or "right". To start your Battlesnake will choose a move randomly. Your goal as a developer is to read information sent to you about the board (available in the `moveRequest` variable) and make an intelligent decision about where your Battlesnake should move next. 

See the [Battlesnake Rules](https://docs.battlesnake.com/rules) for more information on playing the game, moving around the board, and improving your algorithm.

## (Optional) Running Your Battlesnake Locally

Eventually you might want to run your Battlesnake server locally for faster testing and debugging. You can do this by installing [JBang](https://jbang.dev/downloads), then running:

```shell
jbang snake.java
```

This will start the Battlesnake server on port 8080.


**Note:** You cannot create games on [play.battlesnake.com](https://play.battlesnake.com) using a locally running Battlesnake unless you install and use a port forwarding tool like [ngrok](https://ngrok.com/).

---


## Playing Battlesnake

### Completing Challenges

If you're looking for the Single Player Mode of Battlesnake, or something to practice with between events, check out [Challenges.](https://docs.battlesnake.com/guides/quick-start-challenges-guide)

### Joining a Battlesnake Arena

Once you've made your Battlesnake behave and survive on its own, you can enter it into the [Global Battlesnake Arena](https://play.battlesnake.com/arena/global) to see how it performs against other Battlesnakes worldwide.

Arenas will regularly create new games and rank Battlesnakes based on their results. They're a good way to get regular feedback on how well your Battlesnake is performing, and a fun way to track your progress as you develop your algorithm.

### Joining a Battlesnake League

Want to get out there to compete and win prizes? Check out the [Quick Start League Guide](https://docs.battlesnake.com/guides/quick-start-league-guide) for information on the how and when of our competitive seasons.
 
---

## (Optional) Deploying Your First Battlesnake with Heroku

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

1. [Fork this repo](https://github.com/BattlesnakeOfficial/starter-snake-java/fork) into your GitHub Account.

2. [Clone your forked repo](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) into your local environment.
    ```shell
    git clone git@github.com:[YOUR-GITHUB-USERNAME]/starter-snake-java.git
    ```

3. [Create a new Heroku app](https://devcenter.heroku.com/articles/creating-apps) to run your Battlesnake.
    ```shell
    heroku create [YOUR-APP-NAME]
    ```

4. [Deploy your Battlesnake code to Heroku](https://devcenter.heroku.com/articles/git#deploying-code).
    ```shell
    git push heroku master
    ```

5. Open your new Heroku app in your browser.
    ```shell
    heroku open
    ```
    If everything was successful, you should see the following text:
    ```
    {"tailType":"default","color":"#888888","headType":"default","author":"","apiversion":"1"}
    ```

6. Optionally, you can view your server logs using the [Heroku logs command](https://devcenter.heroku.com/articles/logging#log-retrieval) `heroku logs --tail`. The `--tail` option will show a live feed of your logs in real-time.

**At this point your Battlesnake is live and ready to enter games!**

### (Optional) Updating Your Battlesnake with Heroku

After making changes, commit them using git and deploy your changes to Heroku.
```shell
git add .
git commit -m "update my battlesnake's appearance"
git push heroku master
```

Once Heroku has updated you can [create a new game](https://play.battlesnake.com/account/games/create/) with your Battlesnake to view your latest changes in action.

**At this point you should feel comfortable making changes to your code and deploying those changes to Heroku!**
## Resources

All documentation is available at [docs.battlesnake.com](https://docs.battlesnake.com), including detailed Guides, API References, and Tips.

You can also join the Battlesnake Developer Community on [Discord](https://play.battlesnake.com/discord?utm_source=github&utm_medium=readme&utm_campaign=python_starter&utm_content=discord). We have a growing community of Battlesnake developers of all skill levels wanting to help everyone succeed and have fun with Battlesnake :)

Check out live Battlesnake events on [Twitch](https://www.twitch.tv/battlesnakeofficial) and see what is happening when on the [Calendar.](https://play.battlesnake.com/calendar?utm_source=github&utm_medium=readme&utm_campaign=python_starter&utm_content=calendar)

Want to contribute to Battlesnake? We have a number of open-source codebases and would love for you to get involved! Check out our page on [Contributing.](https://docs.battlesnake.com/guides/contributing)
