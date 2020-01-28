*** Settings ***
Library           Selenium2Library
Library           AllureReportLibrary

*** Variables ***
${CREDENTIALS}    key:secret

*** Test Cases ***
Simple Test
    clickxpath    //android.widget.ImageButton[@content-desc=\"메뉴 열림\"]    a, b, c, d
    clickxpath    /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]    a
    clickid    com.ahnlab.v3mobilesecurity.soda:id/tbScope    검사하기
    clickid    com.ahnlab.v3mobilesecurity.soda:id/btnScan    새로운 보안 위협 요소가 없습니다
    clickid    com.ahnlab.v3mobilesecurity.soda:id/tvResult    a

*** Keywords ***