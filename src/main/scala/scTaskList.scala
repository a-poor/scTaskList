// package scTaskList

import java.lang.Thread.sleep
import concurrent.{Await, Future}
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration.Duration

enum TaskStatus:
  case NotStarted, Running, Skipped, Succeded, Failed

trait TaskRunner:
  def run(): Unit
  def getStatus(): TaskStatus
  def setStatus(s: TaskStatus): Unit
  def getError(): Throwable
  def setError(s: Throwable): Unit
  def printTask(indent: Int = 0): Unit
  def clearTask(): Unit

@main def test() = {
  println("Starting...")

  val si = StatusIndicator.newIndicatorRunning()
  for (_ <- 1 to 100) {
    val i = si.getThenInc()
    print("\u001b[K\r" + i + " Running...")
    sleep(100)
  }
  val i = si.getThenInc()
  print("\u001b[K\r" + i + " Running...")

  val res = Future {
    sleep(1000)
    println("Hello, world!")
  }
  println("\nNot done?")

  Await.result(res, Duration.Inf)


  println("Done.")
}
