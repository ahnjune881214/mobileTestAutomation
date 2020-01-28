*** Settings ***
Library           Selenium2Library
Library           AllureReportLibrary

*** Variables ***
${CREDENTIALS}    key:secret

*** Test Cases ***
FirstInstall
    [Documentation]    모바일 테스트 자동화
    m_capture_ele_id    com.android.packageinstaller:id/permission_allow_button
    m_capture_src_id    com.android.packageinstaller:id/permission_allow_button
    M_Click_Id    com.android.packageinstaller:id/permission_allow_button    다음12333
    m_sleep    5000
    M_assert    다음
    M_Click_Id    com.ahnlab.v3mobilesecurity.soda:id/tbEULA
    M_Click_Id    com.ahnlab.v3mobilesecurity.soda:id/btnNext    M_Assert_Id_TW    com.ahnlab.v3mobilesecurity.soda:id/tvScanSubTitle    SURVEY_SCAN_DES01
    m_assert_id_tw    com.ahnlab.v3mobilesecurity.soda:id/tvScanSubTitle    SURVEY_SCAN_DES01
    M_Click_Xpath    /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button    검사 수:
    M_Click_Xpath    /hierarchy/anzdroid.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ImageView
    M_Click_ID    com.ahnlab.v3mobilesecurity.soda:id/btn_push_setting_disagree
    M_Click_accid    메뉴 열림
    m_sleep    5000

*** Keywords ***

