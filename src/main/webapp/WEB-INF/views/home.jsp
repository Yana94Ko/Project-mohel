<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/css/home.css" type="text/css">

<main class="main">
<div class="body-01">

<div class="carousel">
        <div class="carousel-inner">
            <div style="background-color: orange;" class="carousel-item"><h1>First slide</h1></div>
            <div style="background-color: greenyellow;"  class="carousel-item"><h1>Second slide</h1></div>
            <div style="background-color: rgb(37, 150, 255);" class="carousel-item"><h1>Third slide</h1></div>
        </div>
        <div class="carousel-controls">
            <span class="prev"></span>
            <span class="next"></span>
        </div>
        <div class="carousel-indicators"></div>
    </div>
</div>    
<div class="body-02">
 <div class="pricing-table">
    <div class="pricing-card">
	    <div class="pricing-card-01">
	      <h3 class="pricing-card-header">나만의 식단</h3>
	      <div class="price">&nbsp;&nbsp;</div>
	      <ul>
	        <li><strong>3</strong> Websites</li>
	        <li><strong>20 GB</strong> SSD</li>
	        <li><strong>1</strong> Domain Name</li>
	
	      </ul>
	  	</div>
    </div>

    <div class="pricing-card">
	    <div class="pricing-card-02">	
	      <h3 class="pricing-card-header">모두의 식단</h3>
	      <div class="price">&nbsp;&nbsp;</div>
	      <ul>
	        <li><strong>10</strong> Websites</li>
	        <li><strong>50 GB</strong> SSD</li>
	        <li><strong>1</strong> Domain Name</li>
	
	      </ul>
     </div>
    </div>

    <div class="pricing-card">
      <div class="pricing-card-03">		
      <h3 class="pricing-card-header">나만의 운동</h3>
      <div class="price"><i class="bi bi-person-fill"></i></div>
      <ul>
        <li><strong>30</strong> Websites</li>
        <li><strong>150 GB</strong> SSD</li>
        <li><strong>1</strong> Domain Name</li>

      </ul>
  	</div>
    </div>

    <div class="pricing-card">
      <h3 class="pricing-card-header">모두의 운동</h3>
      <div class="price"><i class="bi bi-people-fill"></i></div>
      <ul>
        <li><strong>100</strong> Websites</li>
        <li><strong>500 GB</strong> SSD</li>
        <li><strong>1</strong> Domain Name</li>

      </ul>
  	
    </div>
  </div>
  
 </div>
 <script src="/js/home.js"></script>
 </main>