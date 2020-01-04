package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;

    private String secondName;

    private String describeTask;

    private Date deadlineDate;

    private LocalTime deadlineTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }
    public String getDescribeTask()
    {
        return describeTask;
    }

    public void setDescribeTask(String describeTask)
    {
        this.describeTask = describeTask;
    }

    public Date getDeadlineDate()
    {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate)
    {
        this.deadlineDate = deadlineDate;
    }

    public LocalTime getDeadlineTime()
    {
        return deadlineTime;
    }

    public void setDeadlineTime(LocalTime deadlineTime)
    {

        this.deadlineTime = deadlineTime;
    }


}
