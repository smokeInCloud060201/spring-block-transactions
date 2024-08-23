package speaker

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SpeakerAddLikeSimulator extends Simulation {

  val httpProtocol = http.baseUrl("http://localhost:8080")
    .acceptHeader("*/*")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")

  val addLike = http("Post Json")
    .post("/speaker/addlikes")
    .header("Content-Type", "application/json")
    .body(StringBody("""{
                      "talker_name": "Karson Tang",
                      "like": 1
                    }"""))
    .check(status.is(202))

  val scn = scenario("Add Like Scenario")
    .exec(addLike)

  setUp(
    scn.inject(atOnceUsers(2000))
  ).protocols(httpProtocol)
}
