<%--
  Created by IntelliJ IDEA.
  User: zilet
  Date: 2022-03-02
  Time: 오후 8:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="stripes" %>

<stripes:layout-definition>
    <!DOCTYPE html>
    <html>

        <%@ include file="/WEB-INF/views/reference/head.jsp" %>

        <body>
            <!-- nav -->
            <%@ include file="/WEB-INF/views/reference/nav.jsp" %>

            <!-- left side content (the same left side nav ) -->
            <div id="layoutSidenav">
                <%@ include file="/WEB-INF/views/reference/sidenav.jsp" %>

                <!-- right side content -->
                <div id="layoutSidenav_content">
                    <main>
                        <stripes:layout-component name="contents"/>
                    </main>

                    <%@ include file="/WEB-INF/views/reference/footer.jsp" %>

                </div>

            </div>

        </body>

        <script type="application/javascript">
            $(document).ready(function (){
                $.changeTheme = function (theme) {
                    switch (theme) {
                        case 'dark':
                            $('#sidenavAccordion').removeClass('sb-sidenav-light');
                            $('#sidenavAccordion').addClass('sb-sidenav-dark');
                            $('#navAccordion').removeClass('navbar-light bg-light');
                            $('#navAccordion').addClass('navbar-dark bg-dark');
                            break;
                        case 'light':
                            $('#sidenavAccordion').removeClass('sb-sidenav-dark');
                            $('#sidenavAccordion').addClass('sb-sidenav-light');
                            $('#navAccordion').removeClass('navbar-dark bg-dark');
                            $('#navAccordion').addClass('navbar-light bg-light');
                            break;
                    }
                };

                $('#dark').on('click', function (){
                    $.changeTheme($(this).attr('id'));
                });

                $('#light').on('click', function (){
                    $.changeTheme($(this).attr('id'));
                });

                // $('#sidebarToggle').on('click', function (){
                //     let toggle = 'sb-sidenav-toggled';
                //     if ( $('body').hasClass(toggle) ){
                //         $('body').removeClass(toggle);
                //     } else  {
                //         $('body').addClass(toggle);
                //     }
                // });


            });
        </script>

        <!-- script -->
        <stripes:layout-component name="javascript"/>

    </html>
</stripes:layout-definition>