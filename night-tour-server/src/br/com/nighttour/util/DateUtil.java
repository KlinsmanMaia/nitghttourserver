package br.com.nighttour.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("deprecation")
public final class DateUtil {

    /**
     * Formatador de data.
     */
    private static SimpleDateFormat formatter = new SimpleDateFormat();

    /**
     * Calendar.
     */
    private static Calendar calendar;

    /**
     * private para previnir instancia��o.
     */
    public DateUtil() {
    }

    /**
     * Patterns de data.
     */
    public static enum DatePattern {

        MMMMYYYY("MMMM/yyyy"), YYYY("yyyy"), MMMM("MMMM"), MMAA("MM/yyyy"), DDMMAA("dd/MM/yyyy"), MMDDAA("MM/dd/yyyy"), DDMMAA_HHMMSS(
                "dd/MM/yyyy HH:mm:ss"), DDMMAA_HHMM("dd/MM/yyyy HH:mm"), MMDDAA_HHMMSS("MM/dd/yyyy HH:mm:ss"), HHMM("HH:mm"), HHMMSS("HH:mm:ss");

        /**
         * Pattern.
         */
        private final String pattern;

        private DatePattern(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

    /**
     * Retorna uma instancia de {@link Calendar}.
     *
     * @return Calendar
     */
    public static Calendar getCalendarInstance() {
        if (DateUtil.calendar == null) {
            DateUtil.calendar = new GregorianCalendar();
            DateUtil.calendar = Calendar.getInstance();
        }
        return (Calendar) DateUtil.calendar.clone();
    }

    /**
     * Retorna o {@link Calendar} com a data/hora atual.
     *
     * @return
     */
    public static Calendar getDate() {
        Calendar cal = getCalendarInstance();
        cal.setTime(new Date());
        return cal;
    }

    /**
     * Retorna o pattern default baseado no locale do sistema.
     *
     * @return
     */
    public static String getDefaultPattern() {
        //if (LocaleBuilder.Language.PORTUGUES.equals(LocaleBuilder.getCurrentLanguage())) {
        return DatePattern.DDMMAA.getPattern();
        //}
        //return DatePattern.MMDDAA.getPattern();
    }

    /**
     * Recupera a data como String no formato padrao do sistema.
     *
     * @param data
     * @return String
     */
    public static String getDateAsString(Date data) {
        return getDateAsString(data, getDefaultPattern());
    }

    /**
     * Recupera a data como String no formato informado.
     *
     * @param data
     * @return String
     */
    public static String getDateAsString(Date data, String pattern) {
        if (data != null) {
            formatter.applyPattern(pattern);
            return formatter.format(data);
        }
        return null;
    }

    /**
     * Faz o parser da data informada. A data deve estar sem separador e no
     * formato YYYYMMDD.
     *
     * @param data
     * @return
     */
    /*public static Date parseDateNumeros(String data) {
        if (!StringUtil.notEmpty(data) || data.length() < 8) {
            return null;
        }
        String[] parts = {data.substring(0, 4), data.substring(4, 6), data.substring(6, 8)};
        String completeDate = parts[2] + "/" + parts[1] + "/" + parts[0];
        return parseDate(completeDate);
    }*/

    /**
     * Converte a String para uma data no formato padrao.
     *
     * @param data
     * @return String
     */
    public static Date parseDate(String data) {
        return parseDate(data, getDefaultPattern());
    }

    /**
     * Converte a String para uma data no formato informado.
     *
     * @param data
     * @return String data formatada - null se ParseException ocorrer.
     */
    public static Date parseDate(String data, String pattern) {
        if (data == null) {
            return null;
        }
        formatter.applyPattern(pattern);
        try {
            return formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converte a data informada para {@link Calendar}.
     *
     * @param date
     * @return {@link Calendar}
     */
    public static Calendar getDateAsCalendar(Date date) {
        calendar = getCalendarInstance();
        calendar.setTime(date);
        return (Calendar) calendar.clone();
    }

    /**
     * Converter uma data em string para {@link Calendar}.
     *
     * @param date
     * @return {@link Calendar}
     */
    public static Calendar getStringAsCalendar(String date) {
        return getDateAsCalendar(parseDate(date));
    }

    /**
     * Começo do dia
     *
     * @param calendar
     * @return
     */
    public static Calendar inicioDoDia(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar;
    }

    /**
     * Final do dia
     *
     * @param calendar
     * @return
     */
    public static Calendar fimDoDia(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar;
    }

    /**
     * Começo do dia
     *
     * @param date
     * @return
     */
    public static Calendar inicioDoDia(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        c.set(year, month, day, 0, 0, 0);
        return c;
    }

    /**
     * Final do dia
     *
     * @param date
     * @return
     */
    public static Calendar fimDoDia(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        c.set(year, month, day, 23, 59, 59);
        return c;
    }

    /**
     * Recupera calendar como String no formato padrao do sistema.
     *
     * @param data
     * @return String
     */
    public static String getCalendarAsString(Calendar data) {
        return getCalendarAsString(data, getDefaultPattern());
    }

    /**
     * Recupera o calendar como String no formato informado.
     *
     * @param data
     * @return String
     */
    public static String getCalendarAsString(Calendar data, String pattern) {

        if (data != null) {
            formatter.applyPattern(pattern);
            return formatter.format(data.getTime());
        }
        return null;
    }

    /**
     * Verifica se a data informada e menor que a data atual.
     *
     * @param data
     * @return Boolean
     */
    public static Boolean isDateLowerCurrentDate(Date data) {

        return data.before(getDate().getTime());
    }

    /**
     * Verifica se a data informada e maior que a data atual.
     *
     * @param data
     * @return Boolean
     */
    public static Boolean isDateGreaterCurrentDate(Date data) {

        return data.after(getDate().getTime());
    }

    /**
     * Verifica se a data incial e menor que a data final
     *
     * @param dtInicial
     * @param dtFinal
     * @return boolean
     */
    public static Boolean diffentData(String dtInicial, String dtFinal) {

        return parseDate(dtFinal).before(parseDate(dtInicial));
    }

    /**
     * Verifica se a data incial e menor que a data final
     *
     * @param dtInicial
     * @param dtFinal
     * @return boolean
     */
    public static Boolean diffentData(String dtInicial, String dtFinal, String pattern) {

        return parseDate(dtFinal, pattern).before(parseDate(dtInicial, pattern));
    }

    /**
     * Verifica se a data e válida
     *
     * @param data
     * @return boolean
     */
    public static Boolean validaData(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            format.setLenient(false);
            format.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Calcula a Idade baseado em java.util.Date.
     *
     * @param dataNasc
     * @return int a Idade
     */
    public static int naoFuncionando_calculaIdade(Date dataNasc) {
        //Period periodo = new Period(new DateTime(dataNasc), new DateTime());
        //return periodo.getYears();
        return 0;
    }

    /**
     * Compara duas competencias
     *
     * @param compInicio
     * @param compFinal
     * @return
     */
    public static Boolean diffentCompetencia(String compInicio, String compFinal) {
        return parseDate(compInicio, "MM/yyyy").before(parseDate(compFinal, "MM/yyyy"));
    }

    /**
     * Obtem o mes por extenso
     *
     * @param data
     * @param pattern da data
     * @return String
     */
    public static String getMesExtenso(String data, String pattern) {
        Date date = parseDate(data, pattern);
        formatter.applyPattern(DatePattern.MMMM.getPattern());
        return formatter.format(date);
    }

    /**
     * Obtem somente o ano
     *
     * @param data
     * @param pattern da data
     * @return String
     */
    public static String getAno(String data, String pattern) {
        Date date = parseDate(data, pattern);
        formatter.applyPattern(DatePattern.YYYY.getPattern());
        return formatter.format(date);
    }

    /**
     * Método que retorna uma string do mês e ano corrente.
     *
     * @param data
     * @return String
     */
    public static String getMesAnoString(Date data) {
        formatter.applyPattern(DatePattern.MMMMYYYY.getPattern());
        String mes = formatter.format(data);
        return mes;
    }

    /**
     * Valida se as datas informadas sao equivalentes, considerando dia, mês e
     * ano.
     *
     * @param data1
     * @param data2
     * @return
     */
    public static boolean isMesmaData(Date data1, Date data2) {

        int diaC1 = DateUtil.getDiaMes(data1);
        int diaC2 = DateUtil.getDiaMes(data2);

        int mesC1 = DateUtil.getMes(data1);
        int mesC2 = DateUtil.getMes(data2);

        int anoC1 = DateUtil.getAno(data1);
        int anoC2 = DateUtil.getAno(data2);

        boolean equals = ((diaC1 == diaC2) && (mesC1 == mesC2) && (anoC1 == anoC2));

        return equals;
    }

    /**
     * Retorna o número do dia do mês da data informada.
     *
     * @param data
     * @return int o número do dia do mês da data informada.
     */
    public static int getDiaMes(Date data) {
        Calendar calenda = DateUtil.getCalendarInstance();
        calenda.setTime(data);
        return calenda.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Obtem o número do mês da data informada.
     *
     * @param data
     * @return Integer o número do mês da data informada.
     */
    public static Integer getMes(Date data) {
        Calendar calenda = DateUtil.getCalendarInstance();
        calenda.setTime(data);
        Integer mes = calenda.get(Calendar.MONTH); // +1: Mês começa em zero
        return mes;
    }

    /**
     * Obtem o número do ano da data informada.
     *
     * @param data
     * @return Integer o número do ano da data informada.
     */
    public static Integer getAno(Date data) {
        Calendar calenda = DateUtil.getCalendarInstance();
        calenda.setTime(data);
        Integer ano = calenda.get(Calendar.YEAR);
        return ano;
    }

}
