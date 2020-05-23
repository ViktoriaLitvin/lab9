package bsu.rfe.java.group6.lab9.Litvinenko.metodichka.tag;

import bsu.rfe.java.group6.lab9.Litvinenko.metodichka.entity.Ad;
import bsu.rfe.java.group6.lab9.Litvinenko.metodichka.entity.AdList;
import bsu.rfe.java.group6.lab9.Litvinenko.metodichka.entity.User;
import bsu.rfe.java.group6.lab9.Litvinenko.metodichka.helper.AdListHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Iterator;

public class DeleteAllAd extends SimpleTagSupport {
    // Поле данных для атрибута ad
    private Ad ad;
    // Метод-сеттер для установки атрибута (вызывается контейнером)
    public void setAd(Ad ad) {
        this.ad = ad;
    }
    public void doTag() throws JspException, IOException {
        // Изначально описание ошибки = null (т.е. ошибки нет)
        String errorMessage = null;
        // Извлечь из контекста приложения общий список объявлений
        AdList adList = (AdList) getJspContext().getAttribute("ads", PageContext.APPLICATION_SCOPE);
        // Извлечь из сессии описание текущего пользователя
        User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
        // Проверить, что объявление изменяется его автором, а не чужаком
        // Проверить, что объявление изменяется его автором, а не чужаком

        if (errorMessage == null) {
            Iterator var4 = adList.getAds().iterator();

            while(true) {
                while(var4.hasNext()) {
                    Ad advertisement = (Ad)var4.next();
                    if (currentUser != null && (advertisement.getId() <= 0 || advertisement.getAuthorId() == currentUser.getId())) {
                        adList.deleteAd(advertisement);
                    } else {
                        //errorMessage = "Вы пытаетесь изменить сообщение, к которому не обладаете правами доступа!";
                    }
                }

                AdListHelper.saveAdList(adList);
                break;
            }
        }

        this.getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
    }
}
