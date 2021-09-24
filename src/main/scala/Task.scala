// package scTaskList

class Task(name: String, action: () => Unit) extends TaskRunner:
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
    try {
      action()
      setStatus(TaskStatus.Succeded)
    } catch {
      case e: Throwable => {
        setStatus(TaskStatus.Failed)
        setError(e)
      }
    }
  }

  def printTask(indent: Int = 0): Unit = {
    println(" " * indent + statusIndicator.getThenInc() + " " + name)
  }
  def clearTask(): Unit = {
    print("\u001b[1A")
    print("\u001b[K")
    print("\r")
  }

object Task

