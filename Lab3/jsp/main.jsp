<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>Main</title>
	</head>
	<body>
		<header>
			<div>
				<form method="get" action="/Lab3">
					<div>
						<input type="radio" id="SAX" name="parser" value="0" checked>
						<label for="SAX">SAX</label>
					</div>
					<div>
						<input type="radio" id="StAX" name="parser" value="1">
						<label for="StAX">StAX</label>
					</div>
					<div>
						<input type="radio" id="DOM" name="parser" value="2">
						<label for="DOM">DOM</label>
					</div>
					<input type="submit" value="Parse">
				</form>
			</div>
		</header>
		
		<main>
			<c:if test="${orderList != null}">
				<table border="1">
					<tr>
						<td rowspan="2">id</td>
						<td rowspan="2">Date</td>
						<td colspan="7">Coffee</td>
					</tr>
					<tr>
						<td>Temperature</td>
						<td>Coffee Amount</td>
						<td>Water Volume</td>
						<td>Dilution Water Volume</td>
						<td>Milk Volume</td>
						<td>Milk Foam Volume</td>
						<td>Whipped Cream Volume</td>
					</tr>
					<c:forEach var="order" items="${orderList}">
						<tr>
							<td><c:out value="${order.id}" /></td>
							<td><c:out value="${order.date}" /></td>
							<td><c:out value="${order.coffee.temperature}" /></td>
							<td><c:out value="${order.coffee.coffeeAmount}" /></td>
							<td><c:out value="${order.coffee.waterVolume}" /></td>
							<td>
								<c:if test="${order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.Americano'}">
									<c:out value="${order.coffee.dilutionWaterVolume}" />
								</c:if>
								<c:if test="${order.coffee.coffeeType != 'by.bsuir.machine.beans.coffee.Americano'}">
									---
								</c:if>
							</td>
							<td>
								<c:if test="${(order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.CoffeeWithMilk') ||
												(order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.Cappuccino') ||
												(order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.LatteMacchiato')}">
									<c:out value="${order.coffee.milkVolume}" />
								</c:if>
								<c:if test="${(order.coffee.coffeeType != 'by.bsuir.machine.beans.coffee.CoffeeWithMilk') ||
												(order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.Cappuccino') ||
												(order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.LatteMacchiato')}">
									---
								</c:if>
							</td>
							<td>
								<c:if test="${order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.Cappuccino'}">
									<c:out value="${order.coffee.milkFoamVolume}" />
								</c:if>
								<c:if test="${order.coffee.coffeeType != 'by.bsuir.machine.beans.coffee.Cappuccino'}">
									---
								</c:if>
							</td>
							<td>
								<c:if test="${order.coffee.coffeeType == 'by.bsuir.machine.beans.coffee.LatteMacchiato'}">
									<c:out value="${order.coffee.whippedCreamVolume}" />
								</c:if>
								<c:if test="${order.coffee.coffeeType != 'by.bsuir.machine.beans.coffee.LatteMacchiato'}">
									---
								</c:if>
							</td>
							<td>
								<c:out value="${order.coffee.coffeeType}" />
							</td>
						</tr>
					</c:forEach>
				</table>
				<div>
					<a href="/Lab3?page=prev">Prev</a>
					<a href="/Lab3?page=next">Next</a>
				</div>
			</c:if>
			<c:if test="${orderList == null}">
				<p>Nothing found</p>
			</c:if>
		</main>
	<body>
</html>