import React from 'react';
import { StyleSheet, View, Text, TouchableOpacity, StatusBar } from 'react-native';

const TopBar = ({ onNavigate, onBack, canGoBack, title = 'Guidelines' }) => {
    return (
        <View style={styles.container}>
            <StatusBar barStyle="light-content" backgroundColor="#033c5a" />
            <TouchableOpacity
                style={[styles.backButton, !canGoBack && styles.disabled]}
                onPress={onBack}
                activeOpacity={0.7}
                disabled={!canGoBack}
            >
                <Text style={styles.backArrow}>‹</Text>
                <Text style={styles.backText}>Back</Text>
            </TouchableOpacity>
            <Text style={styles.title} numberOfLines={1} ellipsizeMode="tail">
                {title}
            </Text>
            <TouchableOpacity
                style={styles.homeButton}
                onPress={() => onNavigate('title.html')}
                activeOpacity={0.7}
            >
                <Text style={styles.homeText}>Home</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        backgroundColor: '#033c5a',
        paddingHorizontal: 16,
        paddingVertical: 12,
        elevation: 4,
        shadowColor: '#000',
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.2,
        shadowRadius: 3,
    },
    backButton: {
        flexDirection: 'row',
        alignItems: 'center',
        paddingVertical: 4,
        paddingRight: 8,
        minWidth: 64,
    },
    disabled: {
        opacity: 0.4,
    },
    backArrow: {
        color: '#CC6666',
        fontSize: 28,
        lineHeight: 28,
        marginRight: 2,
        fontWeight: '300',
    },
    backText: {
        color: '#ffffff',
        fontSize: 15,
        fontFamily: 'Verdana',
        fontWeight: '500',
    },
    title: {
        color: '#ffffff',
        fontSize: 16,
        fontFamily: 'Verdana',
        fontWeight: 'bold',
        flex: 1,
        textAlign: 'center',
        letterSpacing: 0.3,
    },
    homeButton: {
        minWidth: 64,
        alignItems: 'flex-end',
        paddingVertical: 4,
    },
    homeText: {
        color: '#ffffff',
        fontSize: 15,
        fontFamily: 'Verdana',
        fontWeight: '500',
    },
});

export default TopBar;