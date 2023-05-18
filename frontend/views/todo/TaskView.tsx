import {useEffect, useState} from "react";
import Task from "Frontend/generated/initiative/hilla/dapr/data/entity/Task.js";
import {TextField} from "@hilla/react-components/TextField.js";
import {Checkbox} from "@hilla/react-components/Checkbox";
import {TaskEndpoint} from "Frontend/generated/endpoints.js";
import {Button} from "@hilla/react-components/Button.js";

export default function TaskView() {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [description, setDescription] = useState('');

  useEffect(() => {
    TaskEndpoint.findAll().then(setTasks);
  }, []);


  async function addTask() {
    const saved = await TaskEndpoint.add(description);
    if (saved) {
      setTasks([...tasks, saved]);
      setDescription('');
    }
  }

  async function updateTask(todo: Task, done: boolean) {
    const saved = await TaskEndpoint.update({
      ...todo, done
    });
    if (saved) {
      setTasks(tasks.map(existing => existing.id === saved.id ? saved : existing));
    }
  }

  return (
    <div className="p-m">
      <h1>Hilla cool todo!</h1>

      <div className="flex gap-s">
        <TextField value={description} onChange={e => setDescription(e.target.value)}/>
        <Button theme="primary" onClick={addTask}>Add</Button>
      </div>

      {tasks.map(task => (
        <div key={task.id}>
          <Checkbox checked={task.done} onCheckedChanged={e => updateTask(task, e.detail.value)}/>
          <span>{task.description}</span>
        </div>
      ))}
    </div>
  );
}
