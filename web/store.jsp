<%-- 
    Document   : store
    Created on : Oct 10, 2023, 10:11:32 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Electro - HTML Ecommerce Template</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script type="text/javascript">
            function setCheck(obj) {
                var fries = document.getElementsByName('cidd');
                if ((obj.id == 'c0') && (fries[0].checked == true)) {
                    for (var i = 1; i < fries.length; i++) {
                        fries[i].checked = false;
                    }
                } else {
                    for (var i = 1; i < fries.length; i++) {
                        if (fries[i].checked == true) {
                            fries[0].checked = false;
                            break;
                        }
                    }
                }
                document.getElementById("f1").submit();
            }
            function setCheck1(obj) {
                var fries = document.getElementsByName('price');
                if ((obj.id == 'g0') && (fries[0].checked == true)) {
                    for (var i = 1; i < fries.length; i++) {
                        fries[i].checked = false;
                    }
                } else {
                    for (var i = 1; i < fries.length; i++) {
                        if (fries[i].checked == true) {
                            fries[0].checked = false;
                            break;
                        }
                    }
                }
                document.getElementById("f2").submit();
            }
        </script>
    </head>
    <body>
        <!-- HEADER -->
        <header>
            <!-- TOP HEADER -->
            <div id="top-header">
                <div class="container">
                    <ul class="header-links pull-left">
                        <li><a href="#"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
                        <li><a href="#"><i class="fa fa-envelope-o"></i> email@email.com</a></li>
                        <li><a href="#"><i class="fa fa-map-marker"></i> 1734 Stonecoal Road</a></li>
                    </ul>
                    <ul class="header-links pull-right">
                        <!--                        <li><a href="#"><i class="fa fa-dollar"></i> USD</a></li>-->
                        <c:if test="${sessionScope.account!=null}">
                            <li><span style="color: red">${sessionScope.account.username}</span>&nbsp;&nbsp;<a href="logout">Log out</a></li>
                            </c:if>    
                            <c:if test="${sessionScope.account==null}">   
                            <li><a href="login"><i class="fa fa-user-o"></i> Sign In</a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
            <!-- /TOP HEADER -->

            <!-- MAIN HEADER -->
            <div id="header">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- LOGO -->
                        <div class="col-md-3">
                            <div class="header-logo">
                                <a href="#" class="logo">
                                    <img src="./img/logo.png" alt="">
                                </a>
                            </div>
                        </div>
                        <!-- /LOGO -->

                        <!-- SEARCH BAR -->
                        <div class="col-md-6">
                            <div class="header-search">
                                <form action="product" method="get">
                                    <select class="input-select">
                                        <option value="0">All Categories</option>
                                        <option value="1">Category 01</option>
                                        <option value="1">Category 02</option>
                                    </select>
                                    <input class="input" placeholder="Search here" name="key">
                                    <button class="search-btn" onclick="this.form.submit()">Search</button>
                                </form>
                            </div>
                        </div>
                        <!-- /SEARCH BAR -->

                        <!-- ACCOUNT -->
                        <div class="col-md-3 clearfix">
                            <div class="header-ctn">
                                <!-- Wishlist -->
                                <!--                                <div>
                                                                    <a href="#">
                                                                        <i class="fa fa-heart-o"></i>
                                                                        <span>Your Wishlist</span>
                                                                        <div class="qty">2</div>
                                                                    </a>
                                                                </div>-->
                                <!-- /Wishlist -->

                                <!-- Cart -->
                                <div class="dropdown">
<!--                                    <a class="dropdown-toggle"  aria-expanded="true" href="show">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Your Cart</span>
                                        <div class="qty">3</div>
                                    </a>-->
                                    <!--                                    <div class="cart-dropdown">
                                                                            <div class="cart-list">
                                                                                <div class="product-widget">
                                                                                    <div class="product-img">
                                                                                        <img src="./img/product01.png" alt="">
                                                                                    </div>
                                                                                    <div class="product-body">
                                                                                        <h3 class="product-name"><a href="#">product name goes here</a></h3>
                                                                                        <h4 class="product-price"><span class="qty">1x</span>$980.00</h4>
                                                                                    </div>
                                                                                    <button class="delete"><i class="fa fa-close"></i></button>
                                                                                </div>
                                    
                                                                                <div class="product-widget">
                                                                                    <div class="product-img">
                                                                                        <img src="./img/product02.png" alt="">
                                                                                    </div>
                                                                                    <div class="product-body">
                                                                                        <h3 class="product-name"><a href="#">product name goes here</a></h3>
                                                                                        <h4 class="product-price"><span class="qty">3x</span>$980.00</h4>
                                                                                    </div>
                                                                                    <button class="delete"><i class="fa fa-close"></i></button>
                                                                                </div>
                                                                            </div>
                                                                            <div class="cart-summary">
                                                                                <small>3 Item(s) selected</small>
                                                                                <h5>SUBTOTAL: $2940.00</h5>
                                                                            </div>
                                                                            <div class="cart-btns">
                                                                                <a href="#">View Cart</a>
                                                                                <a href="#">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>-->
                                </div>
                                <!-- /Cart -->

                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>
                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>
        <!-- /HEADER -->

        <!-- NAVIGATION -->
        <nav id="navigation">
            <!-- container -->
            <div class="container">
                <!-- responsive-nav -->
                <div id="responsive-nav">
                    <!-- NAV -->

                    <ul class="main-nav nav navbar-nav">
                        <li><a href="list">Home</a></li>
                        <li class="${id==0?"active":""}"><a  href="product?id=${0}">Categories</a></li>
                            <c:forEach items="${requestScope.category}" var="c">
                            <li class="${c.id==id?"active":""}"><a href="product?id=${c.id}">${c.name}</a></li>
                            <!--                        <li><a href="#">Smartphones</a></li>
                                                    <li><a href="#">Cameras</a></li>
                                                    <li><a href="#">Accessories</a></li>-->
                        </c:forEach>
                    </ul>
                    <!-- /NAV -->
                </div>
                <!-- /responsive-nav -->
            </div>
            <!-- /container -->
        </nav>
        <!-- /NAVIGATION -->

        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">

        </div>
        <!-- /BREADCRUMB -->

        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- ASIDE -->
                    <div id="aside" class="col-md-3">
                        <!-- aside Widget -->
                        <div class="aside">
                            <h3 class="aside-title">Categories</h3>
                            <div class="checkbox-filter">
                                <c:set var="cid" value="${requestScope.cid}"/>
                                <c:set var="chid" value="${requestScope.chid}"/>
                                <c:set var="cat" value="${requestScope.category}"/>
                                <form action="product2" id="f1" name="f1">
                                    <div class="input-checkbox">             
                                        <input type="checkbox" name="cidd" id="c0" value="${0}" 
                                               ${chid[0]?"checked":""} onclick="setCheck(this)"/>
                                        <label for="c0">
                                            <span></span>
                                            All
                                        </label>
                                    </div>
                                    <c:forEach begin="0" end="${cat.size()-1}" var="i">
                                        <div class="input-checkbox">             
                                            <input type="checkbox" name="cidd" id="n${i}" value="${cat.get(i).getId()}" 
                                                   ${cat.get(i).getId()==cid?"checked":""} ${chid[i+1]?"checked":""} onclick="setCheck(this)"/>
                                            <label for="n${i}">
                                                <span></span>
                                                ${cat.get(i).getName()}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </form>
                                <!--                                <div class="input-checkbox">
                                                                    <input type="checkbox" id="category-2">
                                                                    <label for="category-2">
                                                                        <span></span>
                                                                        Smartphones
                                                                        <small>(740)</small>
                                                                    </label>
                                                                </div>
                                
                                                                <div class="input-checkbox">
                                                                    <input type="checkbox" id="category-3">
                                                                    <label for="category-3">
                                                                        <span></span>
                                                                        Cameras
                                                                        <small>(1450)</small>
                                                                    </label>
                                                                </div>
                                
                                                                <div class="input-checkbox">
                                                                    <input type="checkbox" id="category-4">
                                                                    <label for="category-4">
                                                                        <span></span>
                                                                        Accessories
                                                                        <small>(578)</small>
                                                                    </label>
                                                                </div>
                                
                                                                <div class="input-checkbox">
                                                                    <input type="checkbox" id="category-5">
                                                                    <label for="category-5">
                                                                        <span></span>
                                                                        Laptops
                                                                        <small>(120)</small>
                                                                    </label>
                                                                </div>
                                
                                                                <div class="input-checkbox">
                                                                    <input type="checkbox" id="category-6">
                                                                    <label for="category-6">
                                                                        <span></span>
                                                                        Smartphones
                                                                        <small>(740)</small>
                                                                    </label>
                                                                </div>-->
                            </div>
                        </div>
                        <!-- /aside Widget -->

                        <!-- aside Widget -->
                        <div class="aside">
                            <h3 class="aside-title">Price</h3>
                            <div class="checkbox-filter">
                                <!--                            <div class="price-filter">
                                                                <div id="price-slider"></div>
                                                                <div class="input-number price-min">
                                                                    <input id="price-min" type="number" name="price-min">
                                                                    <span class="qty-up">+</span>
                                                                    <span class="qty-down">-</span>
                                                                </div>
                                                                <span>-</span>
                                                                <div class="input-number price-max">
                                                                    <input id="price-max" type="number" name="price-max">
                                                                    <span class="qty-up">+</span>
                                                                    <span class="qty-down">-</span>
                                                                </div>
                                                            </div>-->
                                <c:set var="pp" value="${requestScope.pp}"/>
                                <c:set var="pb" value="${requestScope.pb}"/>
                                <form action="product2" id="f2">

                                    <div class="input-checkbox">             
                                        <input type="checkbox" name="price" id="g0" value="0" 
                                               ${pb[0]?"checked":""} onclick="setCheck1(this)"/>
                                        <label for="g0">
                                            <span></span>
                                            All
                                        </label>
                                    </div>
                                    <c:forEach begin="0" end="${4}" var="i">
                                        <div class="input-checkbox">

                                            <input type="checkbox" name="price" id="m${i}" value="${(i+1)}" 
                                                   ${pb[i+1]?"checked":""} onclick="setCheck1(this)"/>
                                            <label for="m${i}">
                                                <span></span>
                                                ${pp[i]}
                                            </label>

                                        </div>
                                    </c:forEach>
                                </form>
                            </div>
                        </div>
                        <!-- /aside Widget -->
                        <!-- aside Widget -->
                        <!--                        <div class="aside">
                                                    <h3 class="aside-title">Top selling</h3>
                        <c:forEach items="${requestScope.data}" begin="${1}" end="${4}" var="p">
                        <div class="product-widget">
                            <div class="product-img">
                                <img src="${p.image}" alt="">
                            </div>
                            <div class="product-body">
                                <p class="product-category">${p.category}</p>
                                <h3 class="product-name"><a href="#">${p.name}</a></h3>
                                <h4 class="product-price">${p.price} <del class="product-old-price">${p.price + 100.00}</del></h4>
                            </div>
                        </div>
                        </c:forEach>
                        <div class="product-widget">
                            <div class="product-img">
                                <img src="./img/product02.png" alt="">
                            </div>
                            <div class="product-body">
                                <p class="product-category">Category</p>
                                <h3 class="product-name"><a href="#">product name goes here</a></h3>
                                <h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
                            </div>
                        </div>

                        <div class="product-widget">
                            <div class="product-img">
                                <img src="./img/product03.png" alt="">
                            </div>
                            <div class="product-body">
                                <p class="product-category">Category</p>
                                <h3 class="product-name"><a href="#">product name goes here</a></h3>
                                <h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
                            </div>
                        </div>
                    </div>-->
                        <!-- /aside Widget -->
                    </div>
                    <!-- /ASIDE -->

                    <!-- STORE -->
                    <div id="store" class="col-md-9">
                        <!-- store top filter -->
                        <div class="store-filter clearfix">
                            <div class="store-sort">
                                <label>
                                    Sort By:
                                    <form action="product" method="post">
                                        <select class="input-select" name="sort" onchange="this.form.submit()">
                                            <option value="0" ${requestScope.sort eq '0'?"selected":""}>Price Descending</option>
                                        <option value="1" ${requestScope.sort eq '1'?"selected":""}>Price Ascending</option>
                                    </select>
                                        </form>
                                </label>

                                <label>
                                    Show:
                                    <select class="input-select" name="numper">
                                        <option value="0">9</option>

                                    </select>
                                </label>
                            </div>
                            <ul class="store-grid">
                                <li class="active"><i class="fa fa-th"></i></li>

                            </ul>
                        </div>
                        <!-- /store top filter -->

                        <!-- store products -->
                        <div class="row">
                            <!-- product -->

                            <c:forEach items="${requestScope.data}" var="p">
                                <div class="col-md-4 col-xs-6">
                                    <div class="product">
                                        <div class="product-img">
                                            <img src="img/${p.image}" style="width: 200px;height: 200px" alt="">
                                            <div class="product-label">
                                                <c:if test="${p.sale!=0}">
                                                    <span class="sale">-${p.sale}%</span>
                                                </c:if>
                                                
                                                <span class="new">NEW</span>
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category">${p.category}</p>
                                            <h3 class="product-name"><a href="category?id=${p.id}">${p.name}</a></h3>
                                             
                                                <c:if test="${p.sale!=0}">
                                                <h4 class="product-price"><fmt:formatNumber pattern="##.#" value="${p.price-(p.price*(p.sale/100))}"/>
                                                <del class="product-old-price">${p.price}</del>
                                                </c:if>
                                                <c:if test="${p.sale==0}">
                                                    <h4 class="product-price">${p.price}<h4>
                                                
                                                </c:if>
                                            </h4>
                                            <hr>
                                            <div class="product-btns">
                                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                            </div>
                                        </div>
<!--                                        <div class="add-to-cart">
                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                        </div>-->
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                        <!-- /store products -->

                        <!-- store bottom filter -->
                        <c:set var="page" value="${requestScope.page}"/>
                        <div class="store-filter clearfix">

                            <ul class="store-pagination">
                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <li><a class="${i==page?"active":""}" href="product?page=${i}">${i}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                        <!-- /store bottom filter -->
                    </div>
                    <!-- /STORE -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->

        <!-- NEWSLETTER -->
        <div id="newsletter" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="newsletter">
                            <p>Sign Up for the <strong>NEWSLETTER</strong></p>
                            <form>
                                <input class="input" type="email" placeholder="Enter Your Email">
                                <button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
                            </form>
                            <ul class="newsletter-follow">
                                <li>
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /NEWSLETTER -->

        <!-- FOOTER -->
        <footer id="footer">
            <!-- top footer -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">About Us</h3>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
                                <ul class="footer-links">
                                    <li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
                                    <li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
                                    <li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Categories</h3>
                                <ul class="footer-links">
                                    <li><a href="#">Hot deals</a></li>
                                    <li><a href="#">Laptops</a></li>
                                    <li><a href="#">Smartphones</a></li>
                                    <li><a href="#">Cameras</a></li>
                                    <li><a href="#">Accessories</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="clearfix visible-xs"></div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Information</h3>
                                <ul class="footer-links">
                                    <li><a href="#">About Us</a></li>
                                    <li><a href="#">Contact Us</a></li>
                                    <li><a href="#">Privacy Policy</a></li>
                                    <li><a href="#">Orders and Returns</a></li>
                                    <li><a href="#">Terms & Conditions</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-md-3 col-xs-6">
                            <div class="footer">
                                <h3 class="footer-title">Service</h3>
                                <ul class="footer-links">
                                    <li><a href="#">My Account</a></li>
                                    <li><a href="#">View Cart</a></li>
                                    <li><a href="#">Wishlist</a></li>
                                    <li><a href="#">Track My Order</a></li>
                                    <li><a href="#">Help</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /top footer -->

            <!-- bottom footer -->
            <div id="bottom-footer" class="section">
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <ul class="footer-payments">
                                <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                                <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                                <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                            </ul>
                            <span class="copyright">
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            </span>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /bottom footer -->
        </footer>
        <!-- /FOOTER -->
        <!-- jQuery Plugins -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>

