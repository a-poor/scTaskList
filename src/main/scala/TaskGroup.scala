// package scTaskList

class TaskGroup(name: String, actions: Seq[TaskRunner], parallel: Boolean = false, failOnError: Boolean = false) extends TaskRunner {
  var status = TaskStatus.NotStarted

  var error: Throwable = null

  def getStatus(): TaskStatus = status

  def setStatus(s: TaskStatus): Unit = {
    status = s
  }

  def getError(): Throwable = error

  def setError(e: Throwable): Unit = {
    error = e
  }

  def run(): Unit = {

  }
}

object TaskGroup

