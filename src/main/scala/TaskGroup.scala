// package scTaskList

class TaskGroup(name: String, actions: Seq[TaskRunner], parallel: Boolean = false, failOnError: Boolean = false) extends TaskRunner {
  private var status = TaskStatus.NotStarted
  private var statusIndicator = StatusIndicator.fromStatus(status)
  private var error: Throwable = null

  def getStatus(): TaskStatus = status

  def setStatus(s: TaskStatus): Unit = {
    status = s
    statusIndicator = StatusIndicator.fromStatus(status)
  }

  def getError(): Throwable = error

  def setError(e: Throwable): Unit = {
    error = e
  }

  def run(): Unit = {

  }

  def printTask(indent: Int = 0): Unit = {}
  def clearTask(): Unit = {}

}

object TaskGroup

