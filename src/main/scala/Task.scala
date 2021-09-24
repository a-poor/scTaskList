// package scTaskList

class Task(name: String, action: () => Unit) extends TaskRunner:
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

object Task

