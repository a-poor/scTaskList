// package scTaskList

enum TaskStatus:
  case NotStarted, Running, Skipped, Succeded, Failed

trait TaskRunner:
  def run(): Unit
  def getStatus(): TaskStatus
  def setStatus(s: TaskStatus): Unit
  def getError(): Throwable
  def setError(s: Throwable): Unit

@main def test() = {
  println("Starting...")



  println("Done.")
}
