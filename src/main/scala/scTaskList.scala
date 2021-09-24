// package scTaskList

import java.lang.Thread.sleep
import concurrent.{Await, Future}
import concurrent.ExecutionContext.Implicits.global
import concurrent.duration.Duration

enum TaskStatus:
  case NotStarted, Running, Skipped, Succeeded, Failed

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

  val tl = TaskList(Seq(
    Task("Run A", () => {
      sleep(1000)
    }),
    Task("Then B", () => {
      sleep(1000)
    }),
    Task("End with C", () => {
      sleep(1000)
    }),
  ))

  tl.run()

//  print("\u001b[31mHello World\u001b[0m\n")
//  print("a")
//  sleep(500)
//  print("\u001B[1A") // Move up a line
//
//  print("\u001b[K")  // Clear the line
////  print("\u001b[0K")  // Clear the line
////  print("\u001b[1K")  // Clear the line
////  print("\u001b[2K")  // Clear the line
//  print("\r")        // Move back to tbe beginning of the line

  println("Done.")
}
