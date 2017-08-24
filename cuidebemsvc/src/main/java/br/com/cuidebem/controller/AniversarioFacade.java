package br.com.cuidebem.controller;

import java.math.BigInteger;
import java.time.MonthDay;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.model.view.Aniversario;
import br.com.cuidebem.translate.Bundle;

@Stateless
public class AniversarioFacade extends AbstractFacade<Aniversario> {

	public AniversarioFacade() {
		super(Aniversario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Aniversario> load(Integer idresidencia) {
		List<Aniversario> aniversarios = new ArrayList<Aniversario>();
		Query query = getEntityManager().createNativeQuery(Aniversario.FIND_NIVER);
		query.setParameter(1, idresidencia);
		query.setParameter(2, idresidencia);
		query.setParameter(3, idresidencia);
		List<Object[]> list = query.getResultList();
		for (Object[] o : list) {

			String daymonth = (String) o[3];
			String pattern = "dd-MM";
			MonthDay monthDay = DateUtil.getMonthDay(pattern, daymonth);
			String mes = monthDay.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
			Integer dia = monthDay.getDayOfMonth();
			String nome = (String) o[1];
			String message = "";
			Integer tipousuario = ((BigInteger) o[4]).intValue();
			String link = "";
			switch (tipousuario) {
			case 0:
				link = Bundle.getValue(Aniversario.LINK_PACIENTE, o[0]);
				message = Bundle.getValue(Aniversario.MESSAGE, Bundle.getValue("paciente"),nome, dia, mes);
				break;
			case 1:
				link = Bundle.getValue(Aniversario.LINK_USUARIO,o[0]);
				message = Bundle.getValue(Aniversario.MESSAGE, Bundle.getValue("usuario"), nome, dia, mes);
				break;
			case 2:
				link = Bundle.getValue(Aniversario.LINK_RESPONSAVEL,  o[0], o[5]);
				message = Bundle.getValue(Aniversario.MESSAGE, Bundle.getValue("responsavel"),nome, dia, mes);
				break;
			}
			Aniversario aniversario = new Aniversario(message, link);
			aniversarios.add(aniversario);
		}
		return  aniversarios;
	}
}
