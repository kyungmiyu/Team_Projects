/**
* Template Name: NiceAdmin
* Updated: Jan 29 2024 with Bootstrap v5.3.2
* Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/
(function() {
  "use strict";

  /**
   * Easy selector helper function
   */
  const select = (el, all = false) => {
    el = el.trim()
    if (all) {
      return [...document.querySelectorAll(el)]
    } else {
      return document.querySelector(el)
    }
  }

  /**
   * Easy event listener function
   */
  const on = (type, el, listener, all = false) => {
    if (all) {
      select(el, all).forEach(e => e.addEventListener(type, listener))
    } else {
      select(el, all).addEventListener(type, listener)
    }
  }

  /**
   * Easy on scroll event listener 
   */
  const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
  }

  /**
   * Sidebar toggle
   */
  if (select('.toggle-sidebar-btn')) {
    on('click', '.toggle-sidebar-btn', function(e) {
      select('body').classList.toggle('toggle-sidebar')
    })
  }

  /**
   * Navbar links active state on scroll
   */
  let navbarlinks = select('#navbar .scrollto', true)
  const navbarlinksActive = () => {
    let position = window.scrollY + 200
    navbarlinks.forEach(navbarlink => {
      if (!navbarlink.hash) return
      let section = select(navbarlink.hash)
      if (!section) return
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        navbarlink.classList.add('active')
      } else {
        navbarlink.classList.remove('active')
      }
    })
  }
  window.addEventListener('load', navbarlinksActive)
  onscroll(document, navbarlinksActive)

  /**
   * Toggle .header-scrolled class to #header when page is scrolled
   */
  let selectHeader = select('#header')
  if (selectHeader) {
    const headerScrolled = () => {
      if (window.scrollY > 100) {
        selectHeader.classList.add('header-scrolled')
      } else {
        selectHeader.classList.remove('header-scrolled')
      }
    }
    window.addEventListener('load', headerScrolled)
    onscroll(document, headerScrolled)
  }

  /**
   * Back to top button
   */
  let backtotop = select('.back-to-top')
  if (backtotop) {
    const toggleBacktotop = () => {
      if (window.scrollY > 100) {
        backtotop.classList.add('active')
      } else {
        backtotop.classList.remove('active')
      }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
  }

})();

$('#search-btn').on("click", () => {
  const user_name = $("#search-text").val();

  $(".address-list").empty();
    $.ajax({
      type: "post",
      url: "projectAddressSearch",
      data: JSON.stringify({user_name: user_name}),
      contentType: "application/json",
      dataType: 'json',
      success: function (response) {
        response.forEach((user) => {
          $(".address-list").append(`
            <div class="address-list-card">
              <div class="address-list-card-detail">
                <div class="profile-img-user-name">
                  <img src="/upload/userImg/${user.user_profile}" alt="Profile" class="rounded-circle address-list-profile-img">
                  <p class="user-name">${user.user_name}</p>
                </div>
                <div class="score-message">
                  <div class="user-score rounded-circle">${user.user_score.toFixed(1)}</div>
                  <form action="" method="get">
                    <input type="hidden" name="user_no" value="${user.user_no}">
                    <button type="submit" class="rounded-circle message"><i class="bi bi-envelope-fill"></i></button>
                  </form>
                </div>
              </div>
            </div>
          `);
        });
      }
    });

});

$('#search-text').on('keyup', (event) => {
    if (event.keyCode === 13) {
        $('#search-btn').click(); 
    }
});