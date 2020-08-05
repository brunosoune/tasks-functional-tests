package br.so.polone.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarApplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarApplicacao();
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Fazer café já já");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("05/08/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		}finally {
			//fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarApplicacao();
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("05/08/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		}finally {
			//fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void deveSalvarTarefaSemData() {
		WebDriver driver = acessarApplicacao();
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Tarefa sem data");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		}finally {
			//fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarApplicacao();
		try {
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Arrumar a mesa");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("04/08/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			//fechar browser
			driver.quit();
		}
	}

}
